package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Module;

public interface ModuleRepository extends JpaRepository<Module, Long>{
	Module findById(Long id);
}