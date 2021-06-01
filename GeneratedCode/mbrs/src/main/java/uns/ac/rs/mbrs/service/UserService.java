package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.User;

import java.util.List;
import java.util.Date;

public interface UserService{

	User findOne(Long id); 
	
	User save(User user);

	List<User> findAll();

	User remove(Long id);
	
}