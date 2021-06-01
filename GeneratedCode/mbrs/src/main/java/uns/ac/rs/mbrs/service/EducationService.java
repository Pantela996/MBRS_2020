package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.Education;

import java.util.List;
import java.util.Date;

public interface EducationService{

	Education findOne(Long id); 
	
	Education save(Education education);

	List<Education> findAll();

	Education remove(Long id);
	
}