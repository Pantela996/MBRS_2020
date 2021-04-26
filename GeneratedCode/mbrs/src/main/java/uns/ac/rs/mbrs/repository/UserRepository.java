package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findById(Long id);
}

