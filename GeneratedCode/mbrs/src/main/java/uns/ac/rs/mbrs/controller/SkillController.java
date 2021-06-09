package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.Skill;
import uns.ac.rs.mbrs.service.SkillService;
import uns.ac.rs.mbrs.dto.SkillDTO;

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
@RequestMapping(value="/skill")
public class SkillController {  

	@Autowired
	private SkillService skillService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	String getSkillList (Model model) {

		List<Skill> skillList = skillService.findAll();
		
		List<SkillDTO> skillDTOList =
			skillList
			.stream()
            .map((element) -> modelMapper.map(element, SkillDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", skillDTOList);
		
		return "skill/index";
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

		Skill skill = new Skill(newSkill);
		Skill savedSkill = skillService.save(skill);

		return new ResponseEntity<>(
			modelMapper.map(savedSkill, SkillDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<SkillDTO> edit(@RequestBody @Valid SkillDTO skill, @PathVariable Long id) {

		Skill foundSkill = skillService.findOne(id);
		
		if (foundSkill == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		foundSkill.setName(skill.getName());

		Skill persisted = skillService.save(foundSkill);

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
