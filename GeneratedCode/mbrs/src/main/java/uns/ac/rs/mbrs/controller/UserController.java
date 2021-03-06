package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.User;
import uns.ac.rs.mbrs.service.UserService;
import uns.ac.rs.mbrs.dto.LoginDTO;
import uns.ac.rs.mbrs.dto.UserDTO;
import uns.ac.rs.mbrs.exceptions.EntityAlreadyExistsException;
import uns.ac.rs.mbrs.exceptions.InvalidDataException;
import uns.ac.rs.mbrs.security.TokenUtils;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import uns.ac.rs.mbrs.dto.RegisterDTO;

import org.springframework.ui.Model;

import org.modelmapper.ModelMapper;


@Controller
@RequestMapping(value="/user")
public class UserController {  

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;


	@GetMapping
	String getUserList (Model model) {

		List<User> userList = userService.findAll();
		
		List<UserDTO> userDTOList =
			userList
			.stream()
            .map((element) -> modelMapper.map(element, UserDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", userDTOList);
		
		return "user/index";
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(user, UserDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> add(@RequestBody @Valid UserDTO newUser) {

		User user = new User(newUser);
		User savedUser = userService.save(user);

		return new ResponseEntity<>(
			modelMapper.map(savedUser, UserDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<UserDTO> edit(@RequestBody @Valid UserDTO user, @PathVariable Long id) {

		User foundUser = userService.findOne(id);
		
		if (foundUser == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		foundUser.setUsername(user.getUsername());
		foundUser.setPassword(user.getPassword());
		foundUser.setFirstName(user.getFirstName());
		foundUser.setLastName(user.getLastName());
		foundUser.setSummary(user.getSummary());

		User persisted = userService.save(foundUser);

		return new ResponseEntity<>(
			modelMapper.map(persisted, UserDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		userService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "user/login";
	}

}
