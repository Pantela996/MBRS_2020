package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.Skill;
import uns.ac.rs.mbrs.service.SkillService;
import uns.ac.rs.mbrs.dto.SkillDTO;

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
@RequestMapping(value="/api/skill")
public class SkillController {  

	@Autowired
	private SkillService skillService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<SkillDTO>> getSkillList () {

		List<Skill> skillList = skillService.findAll();
		
		List<SkillDTO> skillDTOList =
			skillList
			.stream()
            .map((element) -> modelMapper.map(element, SkillDTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			skillDTOList,
			HttpStatus.OK
		);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<SkillDTO> getSkill(@PathVariable Long id) {
		Skill skill = skillService.findOne(id);
		if (skill == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(skill, SkillDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<SkillDTO> add(@RequestBody @Valid SkillDTO newSkill) {

		Skill savedSkill = skillService.save(toSkill.convert(newSkill));

		return new ResponseEntity<>(
			modelMapper.map(savedSkill, SkillDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<SkillDTO> edit(@RequestBody @Valid SkillDTO skill, @PathVariable Long id) {

		if (id != skill.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Skill persisted = skillService.save(toSkill.convert(skill));

		return new ResponseEntity<>(
			modelMapper.map(persisted, SkillDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<SkillDTO> delete(@PathVariable Long id) {
		skillService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}