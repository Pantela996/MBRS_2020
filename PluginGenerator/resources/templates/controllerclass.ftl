package ${class.typePackage};

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.${class.name};
import uns.ac.rs.mbrs.service.${class.name}Service;
import uns.ac.rs.mbrs.dto.${class.name}DTO;

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
@RequestMapping(value="/${class.name?uncap_first}")
${class.visibility} class ${class.name}Controller {  

	@Autowired
	private ${class.name}Service ${class.name?uncap_first}Service;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	String get${class.name}List (Model model) {

		List<${class.name}> ${class.name?uncap_first}List = ${class.name?uncap_first}Service.findAll();
		
		List<${class.name}DTO> ${class.name?uncap_first}DTOList =
			${class.name?uncap_first}List
			.stream()
            .map((element) -> modelMapper.map(element, ${class.name}DTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", ${class.name?uncap_first}DTOList);
		
		return "${class.name?uncap_first}/index";
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<${class.name}DTO> get${class.name}(@PathVariable Long id) {
		${class.name} ${class.name?uncap_first} = ${class.name?uncap_first}Service.findOne(id);
		if (${class.name?uncap_first} == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(${class.name?uncap_first}, ${class.name}DTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<${class.name}DTO> add(@RequestBody @Valid ${class.name}DTO new${class.name}) {

		${class.name} ${class.name?uncap_first} = new ${class.name}(new${class.name});
		${class.name} saved${class.name} = ${class.name?uncap_first}Service.save(${class.name?uncap_first});

		return new ResponseEntity<>(
			modelMapper.map(saved${class.name}, ${class.name}DTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<${class.name}DTO> edit(@RequestBody @Valid ${class.name}DTO ${class.name?uncap_first}, @PathVariable Long id) {

		${class.name} found${class.name} = ${class.name?uncap_first}Service.findOne(id);
		
		if (found${class.name} == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		<#list class.properties as property>
		<#if property.type.typePackage?contains("Primitive")>
		found${class.name}.set${property.name?cap_first}(${class.name?uncap_first}.get${property.name?cap_first}());
		</#if>
		</#list>

		${class.name} persisted = ${class.name?uncap_first}Service.save(found${class.name});

		return new ResponseEntity<>(
			modelMapper.map(persisted, ${class.name}DTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<${class.name}DTO> delete(@PathVariable Long id) {
		${class.name?uncap_first}Service.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
