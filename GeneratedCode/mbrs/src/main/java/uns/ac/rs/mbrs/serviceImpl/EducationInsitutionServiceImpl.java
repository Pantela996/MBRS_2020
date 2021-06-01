package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.EducationInsitution;
import uns.ac.rs.mbrs.repository.EducationInsitutionRepository;
import uns.ac.rs.mbrs.service.EducationInsitutionService;

import java.util.Date;


@Service
@Transactional
public class EducationInsitutionServiceImpl implements EducationInsitutionService {

	@Autowired
	private EducationInsitutionRepository educationInsitutionRepository;
	
	
	@Override
	public EducationInsitution findOne(Long id) {
		return educationInsitutionRepository.findById(id).get();
	}

	@Override
	public List<EducationInsitution> findAll() {
		return educationInsitutionRepository.findAll();
	}

	@Override
	public EducationInsitution save(EducationInsitution educationInsitution) {
		return educationInsitutionRepository.save(educationInsitution);
	}
	
	@Override
	public EducationInsitution remove(Long id) {
		EducationInsitution educationInsitution = educationInsitutionRepository.findById(id).get();
		if(educationInsitution == null){
			throw new IllegalArgumentException("Tried to delete :( EducationInsitution");
		}
		educationInsitutionRepository.delete(educationInsitution);
		return educationInsitution;
	}
	
}