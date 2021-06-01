package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.Module;

import java.util.List;
import java.util.Date;

public interface ModuleService{

	Module findOne(Long id); 
	
	Module save(Module module);

	List<Module> findAll();

	Module remove(Long id);
	
}