package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.EducationInstitution;
import uns.ac.rs.mbrs.repository.EducationInstitutionRepository;
import uns.ac.rs.mbrs.service.EducationInstitutionService;

import java.util.Date;


@Service
@Transactional
public class EducationInstitutionServiceImpl implements EducationInstitutionService {

	@Autowired
	private EducationInstitutionRepository educationInstitutionRepository;
	
	
	@Override
	public EducationInstitution findOne(Long id) {
		return educationInstitutionRepository.findById(id).get();
	}

	@Override
	public List<EducationInstitution> findAll() {
		return educationInstitutionRepository.findAll();
	}

	@Override
	public EducationInstitution save(EducationInstitution educationInstitution) {
		return educationInstitutionRepository.save(educationInstitution);
	}
	
	@Override
	public EducationInstitution remove(Long id) {
		EducationInstitution educationInstitution = educationInstitutionRepository.findById(id).get();
		if(educationInstitution == null){
			throw new IllegalArgumentException("Tried to delete :( EducationInstitution");
		}
		educationInstitutionRepository.delete(educationInstitution);
		return educationInstitution;
	}
	
}