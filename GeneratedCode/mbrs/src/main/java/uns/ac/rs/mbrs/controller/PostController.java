package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.Post;
import uns.ac.rs.mbrs.service.PostService;
import uns.ac.rs.mbrs.dto.PostDTO;

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
@RequestMapping(value="/api/post")
public class PostController {  

	@Autowired
	private PostService postService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<PostDTO>> getPostList () {

		List<Post> postList = postService.findAll();
		
		List<PostDTO> postDTOList =
			postList
			.stream()
            .map((element) -> modelMapper.map(element, PostDTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			postDTOList,
			HttpStatus.OK
		);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
		Post post = postService.findOne(id);
		if (post == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(post, PostDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PostDTO> add(@RequestBody @Valid PostDTO newPost) {

		Post savedPost = postService.save(toPost.convert(newPost));

		return new ResponseEntity<>(
			modelMapper.map(savedPost, PostDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<PostDTO> edit(@RequestBody @Valid PostDTO post, @PathVariable Long id) {

		if (id != post.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Post persisted = postService.save(toPost.convert(post));

		return new ResponseEntity<>(
			modelMapper.map(persisted, PostDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<PostDTO> delete(@PathVariable Long id) {
		postService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
