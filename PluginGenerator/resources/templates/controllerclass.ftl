package ${class.typePackage};

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.${class.name};
import uns.ac.rs.mbrs.service.${class.name}Service;
import uns.ac.rs.mbrs.dto.${class.name}DTO;

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
@RequestMapping(value="/api/${class.name?uncap_first}")
${class.visibility} class ${class.name}Controller {  

	@Autowired
	private ${class.name}Service ${class.name?uncap_first}Service;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<${class.name}DTO>> get${class.name}List () {

		List<${class.name}> ${class.name?uncap_first}List = ${class.name?uncap_first}Service.findAll();
		
		List<${class.name}DTO> ${class.name?uncap_first}DTOList =
			${class.name?uncap_first}List
			.stream()
            .map((element) -> modelMapper.map(element, ${class.name}DTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			${class.name?uncap_first}DTOList,
			HttpStatus.OK
		);
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

		${class.name} saved${class.name} = ${class.name?uncap_first}Service.save(to${class.name}.convert(new${class.name}));

		return new ResponseEntity<>(
			modelMapper.map(saved${class.name}, ${class.name}DTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<${class.name}DTO> edit(@RequestBody @Valid ${class.name}DTO ${class.name?uncap_first}, @PathVariable Long id) {

		if (id != ${class.name?uncap_first}.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		${class.name} persisted = ${class.name?uncap_first}Service.save(to${class.name}.convert(${class.name?uncap_first}));

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
