package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.Job;

import java.util.List;
import java.util.Date;

public interface JobService{

	Job findOne(Long id); 
	
	Job save(Job job);

	List<Job> findAll();

	Job remove(Long id);
	
}