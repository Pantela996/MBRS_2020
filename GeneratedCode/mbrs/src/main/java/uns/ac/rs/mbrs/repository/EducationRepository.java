package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Education;

public interface EducationRepository extends JpaRepository<Education, Long>{
	Optional<Education> findById(Long id);
	
	List<Education> findAll();
}

