package ${class.typePackage};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.model.${class.name};
import uns.ac.rs.mbrs.repository.${class.name}Repository;
import uns.ac.rs.mbrs.service.${class.name}Service;
import uns.ac.rs.mbrs.model.*;

import java.util.Date;


@Service
@Transactional
public class ${class.name}ServiceImpl implements ${class.name}Service {

	@Autowired
	private ${class.name}Repository ${class.name?uncap_first}Repository;
	
	
	@Override
	public ${class.name} findOne(Long id) {
		return ${class.name?uncap_first}Repository.findById(id).get();
	}

	@Override
	public List<${class.name}> findAll() {
		return ${class.name?uncap_first}Repository.findAll();
	}

	@Override
	public ${class.name} save(${class.name} ${class.name?uncap_first}) {
		return ${class.name?uncap_first}Repository.save(${class.name?uncap_first});
	}
	
	@Override
	public ${class.name} remove(Long id) {
		${class.name} ${class.name?uncap_first} = ${class.name?uncap_first}Repository.findById(id).get();
		if(${class.name?uncap_first} == null){
			throw new IllegalArgumentException("Tried to delete :( ${class.name}");
		}
		${class.name?uncap_first}Repository.delete(${class.name?uncap_first});
		return ${class.name?uncap_first};
	}
	
}