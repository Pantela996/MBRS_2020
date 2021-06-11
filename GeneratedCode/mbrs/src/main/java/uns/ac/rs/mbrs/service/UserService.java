package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.User;
import uns.ac.rs.mbrs.dto.RegisterDTO;
import uns.ac.rs.mbrs.exceptions.EntityAlreadyExistsException;
import uns.ac.rs.mbrs.exceptions.InvalidDataException;

import java.util.List;
import java.util.Date;

public interface UserService{

	User findOne(Long id); 
	
	User save(User user);

	List<User> findAll();

	User remove(Long id);
	
}