package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.EducationInsitution;

public interface EducationInsitutionRepository extends JpaRepository<EducationInsitution, Long>{
	Optional<EducationInsitution> findById(Long id);
	
	List<EducationInsitution> findAll();
}

