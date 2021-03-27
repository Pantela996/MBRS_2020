package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.EducationInsitution;

public interface EducationInsitutionRepository extends JpaRepository<EducationInsitution, Long>{
	EducationInsitution findById(Long id);
}