package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{
	Skill findById(Long id);
}