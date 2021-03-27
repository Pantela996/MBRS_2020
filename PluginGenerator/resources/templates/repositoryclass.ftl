package ${class.typePackage};

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.${class.name};

public interface ${class.name}Repository extends JpaRepository<${class.name}, Long>{
	${class.name} findById(Long id);
}