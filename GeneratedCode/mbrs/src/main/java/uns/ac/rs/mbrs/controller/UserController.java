package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.User;
import uns.ac.rs.mbrs.service.UserService;
import uns.ac.rs.mbrs.dto.UserDTO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;


@RestController
@RequestMapping(value="/api/user")
public class UserController {  

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<UserDTO>> getUserList () {

		List<User> userList = userService.findAll();
		
		List<UserDTO> userDTOList =
			userList
			.stream()
            .map((element) -> modelMapper.map(element, UserDTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			userDTOList,
			HttpStatus.OK
		);
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

		User savedUser = userService.save(toUser.convert(newUser));

		return new ResponseEntity<>(
			modelMapper.map(savedUser, UserDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<UserDTO> edit(@RequestBody @Valid UserDTO user, @PathVariable Long id) {

		if (id != user.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User persisted = userService.save(toUser.convert(user));

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
	
}
