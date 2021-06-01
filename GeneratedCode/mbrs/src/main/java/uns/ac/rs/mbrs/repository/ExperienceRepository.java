package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long>{
	Optional<Experience> findById(Long id);
	
	List<Experience> findAll();
}

