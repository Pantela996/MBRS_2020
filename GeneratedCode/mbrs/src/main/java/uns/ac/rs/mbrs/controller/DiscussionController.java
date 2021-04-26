package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.Discussion;
import uns.ac.rs.mbrs.service.DiscussionService;
import uns.ac.rs.mbrs.dto.DiscussionDTO;

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
@RequestMapping(value="/api/discussion")
public class DiscussionController {  

	@Autowired
	private DiscussionService discussionService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<DiscussionDTO>> getDiscussionList () {

		List<Discussion> discussionList = discussionService.findAll();
		
		List<DiscussionDTO> discussionDTOList =
			discussionList
			.stream()
            .map((element) -> modelMapper.map(element, DiscussionDTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			discussionDTOList,
			HttpStatus.OK
		);
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

		Discussion savedDiscussion = discussionService.save(toDiscussion.convert(newDiscussion));

		return new ResponseEntity<>(
			modelMapper.map(savedDiscussion, DiscussionDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<DiscussionDTO> edit(@RequestBody @Valid DiscussionDTO discussion, @PathVariable Long id) {

		if (id != discussion.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Discussion persisted = discussionService.save(toDiscussion.convert(discussion));

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
