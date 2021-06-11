package uns.ac.rs.mbrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uns.ac.rs.mbrs.dto.LoginDTO;
import uns.ac.rs.mbrs.dto.RegisterDTO;
import uns.ac.rs.mbrs.exceptions.EntityAlreadyExistsException;
import uns.ac.rs.mbrs.exceptions.InvalidDataException;
import uns.ac.rs.mbrs.security.TokenUtils;
import uns.ac.rs.mbrs.service.UserAuthService;

@Controller
public class UserAuthController {

	@Autowired
	private UserAuthService userAuthService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
		try {
        	// Perform the authentication
        	UsernamePasswordAuthenticationToken token = 
        			new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);            
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Reload user details so we can generate token
            UserDetails details = userDetailsService.
            		loadUserByUsername(loginDTO.getUsername());
            return new ResponseEntity<String>(
            		tokenUtils.generateToken(details), HttpStatus.OK);
        } catch (Exception ex) {
//        	System.out.println(ex.getMessage());
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
		try {
			userAuthService.registerUser(registerDTO);
		} catch (InvalidDataException | EntityAlreadyExistsException e) {
			return new ResponseEntity<String>("Invalid register.." + e.getMessage() ,HttpStatus.BAD_REQUEST);
		}
		
        return new ResponseEntity<String>("Successful register",HttpStatus.OK);
	}
}
