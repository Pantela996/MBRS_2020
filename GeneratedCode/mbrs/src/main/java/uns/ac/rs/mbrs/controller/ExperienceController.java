package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.Experience;
import uns.ac.rs.mbrs.service.ExperienceService;
import uns.ac.rs.mbrs.dto.ExperienceDTO;

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
@RequestMapping(value="/experience")
public class ExperienceController {  

	@Autowired
	private ExperienceService experienceService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	String getExperienceList (Model model) {

		List<Experience> experienceList = experienceService.findAll();
		
		List<ExperienceDTO> experienceDTOList =
			experienceList
			.stream()
            .map((element) -> modelMapper.map(element, ExperienceDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", experienceDTOList);
		
		return "experience/index";
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ExperienceDTO> getExperience(@PathVariable Long id) {
		Experience experience = experienceService.findOne(id);
		if (experience == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(experience, ExperienceDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ExperienceDTO> add(@RequestBody @Valid ExperienceDTO newExperience) {

		Experience experience = new Experience(newExperience);
		Experience savedExperience = experienceService.save(experience);

		return new ResponseEntity<>(
			modelMapper.map(savedExperience, ExperienceDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<ExperienceDTO> edit(@RequestBody @Valid ExperienceDTO experience, @PathVariable Long id) {

		Experience foundExperience = experienceService.findOne(id);
		
		if (foundExperience == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


		Experience persisted = experienceService.save(foundExperience);

		return new ResponseEntity<>(
			modelMapper.map(persisted, ExperienceDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<ExperienceDTO> delete(@PathVariable Long id) {
		experienceService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
