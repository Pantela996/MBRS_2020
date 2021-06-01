package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{
	Optional<Skill> findById(Long id);
	
	List<Skill> findAll();
}

