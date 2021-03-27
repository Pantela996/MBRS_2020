package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Education;

public interface EducationRepository extends JpaRepository<Education, Long>{
	Education findById(Long id);
}