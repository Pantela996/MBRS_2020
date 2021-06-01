package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.Module;
import uns.ac.rs.mbrs.repository.ModuleRepository;
import uns.ac.rs.mbrs.service.ModuleService;

import java.util.Date;


@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;
	
	
	@Override
	public Module findOne(Long id) {
		return moduleRepository.findById(id).get();
	}

	@Override
	public List<Module> findAll() {
		return moduleRepository.findAll();
	}

	@Override
	public Module save(Module module) {
		return moduleRepository.save(module);
	}
	
	@Override
	public Module remove(Long id) {
		Module module = moduleRepository.findById(id).get();
		if(module == null){
			throw new IllegalArgumentException("Tried to delete :( Module");
		}
		moduleRepository.delete(module);
		return module;
	}
	
}