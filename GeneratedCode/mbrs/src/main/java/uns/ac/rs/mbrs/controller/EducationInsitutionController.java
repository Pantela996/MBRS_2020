package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.EducationInsitution;
import uns.ac.rs.mbrs.service.EducationInsitutionService;
import uns.ac.rs.mbrs.dto.EducationInsitutionDTO;

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
@RequestMapping(value="/api/educationInsitution")
public class EducationInsitutionController {  

	@Autowired
	private EducationInsitutionService educationInsitutionService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<EducationInsitutionDTO>> getEducationInsitutionList () {

		List<EducationInsitution> educationInsitutionList = educationInsitutionService.findAll();
		
		List<EducationInsitutionDTO> educationInsitutionDTOList =
			educationInsitutionList
			.stream()
            .map((element) -> modelMapper.map(element, EducationInsitutionDTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			educationInsitutionDTOList,
			HttpStatus.OK
		);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<EducationInsitutionDTO> getEducationInsitution(@PathVariable Long id) {
		EducationInsitution educationInsitution = educationInsitutionService.findOne(id);
		if (educationInsitution == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(educationInsitution, EducationInsitutionDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<EducationInsitutionDTO> add(@RequestBody @Valid EducationInsitutionDTO newEducationInsitution) {

		EducationInsitution savedEducationInsitution = educationInsitutionService.save(toEducationInsitution.convert(newEducationInsitution));

		return new ResponseEntity<>(
			modelMapper.map(savedEducationInsitution, EducationInsitutionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<EducationInsitutionDTO> edit(@RequestBody @Valid EducationInsitutionDTO educationInsitution, @PathVariable Long id) {

		if (id != educationInsitution.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		EducationInsitution persisted = educationInsitutionService.save(toEducationInsitution.convert(educationInsitution));

		return new ResponseEntity<>(
			modelMapper.map(persisted, EducationInsitutionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<EducationInsitutionDTO> delete(@PathVariable Long id) {
		educationInsitutionService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
