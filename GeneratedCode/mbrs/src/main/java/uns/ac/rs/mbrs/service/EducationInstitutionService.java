package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.EducationInstitution;

import java.util.List;
import java.util.Date;

public interface EducationInstitutionService{

	EducationInstitution findOne(Long id); 
	
	EducationInstitution save(EducationInstitution educationInstitution);

	List<EducationInstitution> findAll();

	EducationInstitution remove(Long id);
	
}