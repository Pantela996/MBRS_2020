package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	Optional<Job> findById(Long id);
	
	List<Job> findAll();
}

