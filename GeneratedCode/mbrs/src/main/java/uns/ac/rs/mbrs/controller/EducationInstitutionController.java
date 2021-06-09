package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.EducationInstitution;
import uns.ac.rs.mbrs.service.EducationInstitutionService;
import uns.ac.rs.mbrs.dto.EducationInstitutionDTO;

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
@RequestMapping(value="/educationInstitution")
public class EducationInstitutionController {  

	@Autowired
	private EducationInstitutionService educationInstitutionService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	String getEducationInstitutionList (Model model) {

		List<EducationInstitution> educationInstitutionList = educationInstitutionService.findAll();
		
		List<EducationInstitutionDTO> educationInstitutionDTOList =
			educationInstitutionList
			.stream()
            .map((element) -> modelMapper.map(element, EducationInstitutionDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", educationInstitutionDTOList);
		
		return "educationInstitution/index";
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<EducationInstitutionDTO> getEducationInstitution(@PathVariable Long id) {
		EducationInstitution educationInstitution = educationInstitutionService.findOne(id);
		if (educationInstitution == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(educationInstitution, EducationInstitutionDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<EducationInstitutionDTO> add(@RequestBody @Valid EducationInstitutionDTO newEducationInstitution) {

		EducationInstitution educationInstitution = new EducationInstitution(newEducationInstitution);
		EducationInstitution savedEducationInstitution = educationInstitutionService.save(educationInstitution);

		return new ResponseEntity<>(
			modelMapper.map(savedEducationInstitution, EducationInstitutionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<EducationInstitutionDTO> edit(@RequestBody @Valid EducationInstitutionDTO educationInstitution, @PathVariable Long id) {

		EducationInstitution foundEducationInstitution = educationInstitutionService.findOne(id);
		
		if (foundEducationInstitution == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		foundEducationInstitution.setName(educationInstitution.getName());

		EducationInstitution persisted = educationInstitutionService.save(foundEducationInstitution);

		return new ResponseEntity<>(
			modelMapper.map(persisted, EducationInstitutionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<EducationInstitutionDTO> delete(@PathVariable Long id) {
		educationInstitutionService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
