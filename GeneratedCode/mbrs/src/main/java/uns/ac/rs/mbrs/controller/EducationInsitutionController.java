package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.EducationInsitution;
import uns.ac.rs.mbrs.service.EducationInsitutionService;
import uns.ac.rs.mbrs.dto.EducationInsitutionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import org.modelmapper.ModelMapper;


@RestController
@RequestMapping(value="/api/educationInsitution")
public class EducationInsitutionController {  

	@Autowired
	private EducationInsitutionService educationInsitutionService;
	
	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.GET)
	String getEducationInsitutionList (Model model) {

		List<EducationInsitution> educationInsitutionList = educationInsitutionService.findAll();
		
		List<EducationInsitutionDTO> educationInsitutionDTOList =
			educationInsitutionList
			.stream()
            .map((element) -> modelMapper.map(element, EducationInsitutionDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", educationInsitutionDTOList);
		
		return "educationInsitution/index";
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

		EducationInsitution educationInsitution = new EducationInsitution(newEducationInsitution);
		EducationInsitution savedEducationInsitution = educationInsitutionService.save(educationInsitution);

		return new ResponseEntity<>(
			modelMapper.map(savedEducationInsitution, EducationInsitutionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<EducationInsitutionDTO> edit(@RequestBody @Valid EducationInsitutionDTO educationInsitution, @PathVariable Long id) {

		EducationInsitution foundEducationInsitution = educationInsitutionService.findOne(id);
		
		if (foundEducationInsitution == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		foundEducationInsitution.setName(educationInsitution.getName());

		EducationInsitution persisted = educationInsitutionService.save(foundEducationInsitution);

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
