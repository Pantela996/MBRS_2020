package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.Experience;

import java.util.List;
import java.util.Date;

public interface ExperienceService{

	Experience findOne(Long id); 
	
	Experience save(Experience experience);

	List<Experience> findAll();

	Experience remove(Long id);
	
}