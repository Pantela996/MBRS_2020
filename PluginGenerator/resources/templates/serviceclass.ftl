package ${class.typePackage};

import import uns.ac.rs.mbrs.model.${class.name};

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;

public interface ${class.name}Service{

	${class.name} findOne(Long id); 
	
	${class.name} save(${class.name} ${class.name?uncap_first});

	List<${class.name}> findAll();

	${class.name} remove(Long id);
	
}