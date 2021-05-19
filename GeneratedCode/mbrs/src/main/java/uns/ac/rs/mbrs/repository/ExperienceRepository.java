package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long>{
	Experience findById(Long id);
}

