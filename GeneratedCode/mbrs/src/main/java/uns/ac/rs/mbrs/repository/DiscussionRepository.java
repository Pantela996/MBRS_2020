package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Discussion;

public interface DiscussionRepository extends JpaRepository<Discussion, Long>{
	Optional<Discussion> findById(Long id);
	
	List<Discussion> findAll();
}

