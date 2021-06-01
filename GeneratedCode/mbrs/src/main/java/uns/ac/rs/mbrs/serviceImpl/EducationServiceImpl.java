package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.Education;
import uns.ac.rs.mbrs.repository.EducationRepository;
import uns.ac.rs.mbrs.service.EducationService;

import java.util.Date;


@Service
@Transactional
public class EducationServiceImpl implements EducationService {

	@Autowired
	private EducationRepository educationRepository;
	
	
	@Override
	public Education findOne(Long id) {
		return educationRepository.findById(id).get();
	}

	@Override
	public List<Education> findAll() {
		return educationRepository.findAll();
	}

	@Override
	public Education save(Education education) {
		return educationRepository.save(education);
	}
	
	@Override
	public Education remove(Long id) {
		Education education = educationRepository.findById(id).get();
		if(education == null){
			throw new IllegalArgumentException("Tried to delete :( Education");
		}
		educationRepository.delete(education);
		return education;
	}
	
}