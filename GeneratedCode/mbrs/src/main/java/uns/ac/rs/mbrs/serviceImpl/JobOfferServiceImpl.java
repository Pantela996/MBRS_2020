package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.JobOffer;
import uns.ac.rs.mbrs.repository.JobOfferRepository;
import uns.ac.rs.mbrs.service.JobOfferService;

import java.util.Date;


@Service
@Transactional
public class JobOfferServiceImpl implements JobOfferService {

	@Autowired
	private JobOfferRepository jobOfferRepository;
	
	
	@Override
	public JobOffer findOne(Long id) {
		return jobOfferRepository.findById(id).get();
	}

	@Override
	public List<JobOffer> findAll() {
		return jobOfferRepository.findAll();
	}

	@Override
	public JobOffer save(JobOffer jobOffer) {
		return jobOfferRepository.save(jobOffer);
	}
	
	@Override
	public JobOffer remove(Long id) {
		JobOffer jobOffer = jobOfferRepository.findById(id).get();
		if(jobOffer == null){
			throw new IllegalArgumentException("Tried to delete :( JobOffer");
		}
		jobOfferRepository.delete(jobOffer);
		return jobOffer;
	}
	
}