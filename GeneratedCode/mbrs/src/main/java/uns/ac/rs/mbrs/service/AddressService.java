package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.Address;

import java.util.List;
import java.util.Date;

public interface AddressService{

	Address findOne(Long id); 
	
	Address save(Address address);

	List<Address> findAll();

	Address remove(Long id);
	
}