package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.EducationInsitution;

import java.util.List;
import java.util.Date;

public interface EducationInsitutionService{

	EducationInsitution findOne(Long id); 
	
	EducationInsitution save(EducationInsitution educationInsitution);

	List<EducationInsitution> findAll();

	EducationInsitution remove(Long id);
	
}