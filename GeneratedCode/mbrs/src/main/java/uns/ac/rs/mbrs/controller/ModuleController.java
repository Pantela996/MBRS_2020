package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.Module;
import uns.ac.rs.mbrs.service.ModuleService;
import uns.ac.rs.mbrs.dto.ModuleDTO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;


@RestController
@RequestMapping(value="/api/module")
public class ModuleController {  

	@Autowired
	private ModuleService moduleService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<ModuleDTO>> getModuleList () {

		List<Module> moduleList = moduleService.findAll();
		
		List<ModuleDTO> moduleDTOList =
			moduleList
			.stream()
            .map((element) -> modelMapper.map(element, ModuleDTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			moduleDTOList,
			HttpStatus.OK
		);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ModuleDTO> getModule(@PathVariable Long id) {
		Module module = moduleService.findOne(id);
		if (module == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(module, ModuleDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ModuleDTO> add(@RequestBody @Valid ModuleDTO newModule) {

		Module savedModule = moduleService.save(toModule.convert(newModule));

		return new ResponseEntity<>(
			modelMapper.map(savedModule, ModuleDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<ModuleDTO> edit(@RequestBody @Valid ModuleDTO module, @PathVariable Long id) {

		if (id != module.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Module persisted = moduleService.save(toModule.convert(module));

		return new ResponseEntity<>(
			modelMapper.map(persisted, ModuleDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<ModuleDTO> delete(@PathVariable Long id) {
		moduleService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
