package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.Discussion;
import uns.ac.rs.mbrs.service.DiscussionService;
import uns.ac.rs.mbrs.dto.DiscussionDTO;

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
@RequestMapping(value="/api/discussion")
public class DiscussionController {  

	@Autowired
	private DiscussionService discussionService;
	
	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.GET)
	String getDiscussionList (Model model) {

		List<Discussion> discussionList = discussionService.findAll();
		
		List<DiscussionDTO> discussionDTOList =
			discussionList
			.stream()
            .map((element) -> modelMapper.map(element, DiscussionDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", discussionDTOList);
		
		return "discussion/index";
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<DiscussionDTO> getDiscussion(@PathVariable Long id) {
		Discussion discussion = discussionService.findOne(id);
		if (discussion == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(discussion, DiscussionDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<DiscussionDTO> add(@RequestBody @Valid DiscussionDTO newDiscussion) {

		Discussion discussion = new Discussion(newDiscussion);
		Discussion savedDiscussion = discussionService.save(discussion);

		return new ResponseEntity<>(
			modelMapper.map(savedDiscussion, DiscussionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<DiscussionDTO> edit(@RequestBody @Valid DiscussionDTO discussion, @PathVariable Long id) {

		Discussion foundDiscussion = discussionService.findOne(id);
		
		if (foundDiscussion == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		foundDiscussion.setTopic(discussion.getTopic());

		Discussion persisted = discussionService.save(foundDiscussion);

		return new ResponseEntity<>(
			modelMapper.map(persisted, DiscussionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<DiscussionDTO> delete(@PathVariable Long id) {
		discussionService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
