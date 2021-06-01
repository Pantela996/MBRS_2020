package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.JobOffer;

import java.util.List;
import java.util.Date;

public interface JobOfferService{

	JobOffer findOne(Long id); 
	
	JobOffer save(JobOffer jobOffer);

	List<JobOffer> findAll();

	JobOffer remove(Long id);
	
}