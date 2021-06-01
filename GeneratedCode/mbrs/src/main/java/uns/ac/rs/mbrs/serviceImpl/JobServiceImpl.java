package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.Job;
import uns.ac.rs.mbrs.repository.JobRepository;
import uns.ac.rs.mbrs.service.JobService;

import java.util.Date;


@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;
	
	
	@Override
	public Job findOne(Long id) {
		return jobRepository.findById(id).get();
	}

	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
	}

	@Override
	public Job save(Job job) {
		return jobRepository.save(job);
	}
	
	@Override
	public Job remove(Long id) {
		Job job = jobRepository.findById(id).get();
		if(job == null){
			throw new IllegalArgumentException("Tried to delete :( Job");
		}
		jobRepository.delete(job);
		return job;
	}
	
}