package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.Discussion;

import java.util.List;
import java.util.Date;

public interface DiscussionService{

	Discussion findOne(Long id); 
	
	Discussion save(Discussion discussion);

	List<Discussion> findAll();

	Discussion remove(Long id);
	
}