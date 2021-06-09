package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.Module;
import uns.ac.rs.mbrs.service.ModuleService;
import uns.ac.rs.mbrs.dto.ModuleDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import org.modelmapper.ModelMapper;


@Controller
@RequestMapping(value="/module")
public class ModuleController {  

	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	String getModuleList (Model model) {

		List<Module> moduleList = moduleService.findAll();
		
		List<ModuleDTO> moduleDTOList =
			moduleList
			.stream()
            .map((element) -> modelMapper.map(element, ModuleDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", moduleDTOList);
		
		return "module/index";
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

		Module module = new Module(newModule);
		Module savedModule = moduleService.save(module);

		return new ResponseEntity<>(
			modelMapper.map(savedModule, ModuleDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<ModuleDTO> edit(@RequestBody @Valid ModuleDTO module, @PathVariable Long id) {

		Module foundModule = moduleService.findOne(id);
		
		if (foundModule == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		foundModule.setName(module.getName());

		Module persisted = moduleService.save(foundModule);

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
