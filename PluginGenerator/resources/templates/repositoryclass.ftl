package ${class.typePackage};

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.${class.name};

public interface ${class.name}Repository extends JpaRepository<${class.name}, Long>{
	Optional<${class.name}> findById(Long id);
	
	List<${class.name}> findAll();
}

