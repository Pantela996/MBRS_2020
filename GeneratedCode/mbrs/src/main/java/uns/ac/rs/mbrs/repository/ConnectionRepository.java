package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Long>{
	Connection findById(Long id);
}

