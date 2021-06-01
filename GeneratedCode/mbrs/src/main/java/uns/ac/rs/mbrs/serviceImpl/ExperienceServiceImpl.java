package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.Experience;
import uns.ac.rs.mbrs.repository.ExperienceRepository;
import uns.ac.rs.mbrs.service.ExperienceService;

import java.util.Date;


@Service
@Transactional
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	private ExperienceRepository experienceRepository;
	
	
	@Override
	public Experience findOne(Long id) {
		return experienceRepository.findById(id).get();
	}

	@Override
	public List<Experience> findAll() {
		return experienceRepository.findAll();
	}

	@Override
	public Experience save(Experience experience) {
		return experienceRepository.save(experience);
	}
	
	@Override
	public Experience remove(Long id) {
		Experience experience = experienceRepository.findById(id).get();
		if(experience == null){
			throw new IllegalArgumentException("Tried to delete :( Experience");
		}
		experienceRepository.delete(experience);
		return experience;
	}
	
}