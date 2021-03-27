package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	Job findById(Long id);
}