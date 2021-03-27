package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Discussion;

public interface DiscussionRepository extends JpaRepository<Discussion, Long>{
	Discussion findById(Long id);
}