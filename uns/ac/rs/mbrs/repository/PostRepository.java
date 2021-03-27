package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	Post findById(Long id);
}