package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	Optional<Post> findById(Long id);
	
	List<Post> findAll();
}

