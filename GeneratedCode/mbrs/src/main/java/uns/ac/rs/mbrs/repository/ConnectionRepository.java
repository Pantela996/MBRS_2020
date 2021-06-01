package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Long>{
	Optional<Connection> findById(Long id);
	
	List<Connection> findAll();
}

