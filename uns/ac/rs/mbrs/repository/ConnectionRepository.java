package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Long>{
	Connection findById(Long id);
}