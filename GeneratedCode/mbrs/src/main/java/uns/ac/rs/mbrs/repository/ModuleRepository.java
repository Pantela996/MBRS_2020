package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Module;

public interface ModuleRepository extends JpaRepository<Module, Long>{
	Optional<Module> findById(Long id);
	
	List<Module> findAll();
}

