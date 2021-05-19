package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Education;

public interface EducationRepository extends JpaRepository<Education, Long>{
	Education findById(Long id);
}

