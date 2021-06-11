package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findById(Long id);
	
	List<User> findAll();
	
}

