package ${class.typePackage};

import uns.ac.rs.mbrs.domain.${class.name};

import java.util.List;
import java.util.Date;

public interface ${class.name}Service{

	${class.name} findOne(Long id); 
	
	${class.name} save(${class.name} ${class.name?uncap_first});

	List<${class.name}> findAll();

	${class.name} remove(Long id);
	
}