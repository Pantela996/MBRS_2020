package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.Connection;

import java.util.List;
import java.util.Date;

public interface ConnectionService{

	Connection findOne(Long id); 
	
	Connection save(Connection connection);

	List<Connection> findAll();

	Connection remove(Long id);
	
}