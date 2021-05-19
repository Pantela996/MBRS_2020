package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	Job findById(Long id);
}

