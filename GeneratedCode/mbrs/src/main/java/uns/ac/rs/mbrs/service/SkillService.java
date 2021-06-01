package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.Skill;

import java.util.List;
import java.util.Date;

public interface SkillService{

	Skill findOne(Long id); 
	
	Skill save(Skill skill);

	List<Skill> findAll();

	Skill remove(Long id);
	
}