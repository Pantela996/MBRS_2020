package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.Skill;
import uns.ac.rs.mbrs.repository.SkillRepository;
import uns.ac.rs.mbrs.service.SkillService;

import java.util.Date;


@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;
	
	
	@Override
	public Skill findOne(Long id) {
		return skillRepository.findById(id).get();
	}

	@Override
	public List<Skill> findAll() {
		return skillRepository.findAll();
	}

	@Override
	public Skill save(Skill skill) {
		return skillRepository.save(skill);
	}
	
	@Override
	public Skill remove(Long id) {
		Skill skill = skillRepository.findById(id).get();
		if(skill == null){
			throw new IllegalArgumentException("Tried to delete :( Skill");
		}
		skillRepository.delete(skill);
		return skill;
	}
	
}