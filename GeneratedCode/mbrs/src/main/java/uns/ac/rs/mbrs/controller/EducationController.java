package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.Education;
import uns.ac.rs.mbrs.service.EducationService;
import uns.ac.rs.mbrs.dto.EducationDTO;

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
@RequestMapping(value="/api/education")
public class EducationController {  

	@Autowired
	private EducationService educationService;
	
	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.GET)
	String getEducationList (Model model) {

		List<Education> educationList = educationService.findAll();
		
		List<EducationDTO> educationDTOList =
			educationList
			.stream()
            .map((element) -> modelMapper.map(element, EducationDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", educationDTOList);
		
		return "education/index";
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<EducationDTO> getEducation(@PathVariable Long id) {
		Education education = educationService.findOne(id);
		if (education == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(education, EducationDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<EducationDTO> add(@RequestBody @Valid EducationDTO newEducation) {

		Education education = new Education(newEducation);
		Education savedEducation = educationService.save(education);

		return new ResponseEntity<>(
			modelMapper.map(savedEducation, EducationDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<EducationDTO> edit(@RequestBody @Valid EducationDTO education, @PathVariable Long id) {

		Education foundEducation = educationService.findOne(id);
		
		if (foundEducation == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


		Education persisted = educationService.save(foundEducation);

		return new ResponseEntity<>(
			modelMapper.map(persisted, EducationDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<EducationDTO> delete(@PathVariable Long id) {
		educationService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
