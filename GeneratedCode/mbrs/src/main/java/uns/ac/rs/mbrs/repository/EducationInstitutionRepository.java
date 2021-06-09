package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.EducationInstitution;

public interface EducationInstitutionRepository extends JpaRepository<EducationInstitution, Long>{
	Optional<EducationInstitution> findById(Long id);
	
	List<EducationInstitution> findAll();
}

