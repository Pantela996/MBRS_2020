package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.validation.Valid;

import uns.ac.rs.mbrs.domain.Post;
import uns.ac.rs.mbrs.service.PostService;
import uns.ac.rs.mbrs.dto.PostDTO;

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
@RequestMapping(value="/api/post")
public class PostController {  

	@Autowired
	private PostService postService;
	
	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.GET)
	String getPostList (Model model) {

		List<Post> postList = postService.findAll();
		
		List<PostDTO> postDTOList =
			postList
			.stream()
            .map((element) -> modelMapper.map(element, PostDTO.class))
            .collect(Collectors.toList());
			
		model.addAttribute("list", postDTOList);
		
		return "post/index";
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

		Post post = new Post(newPost);
		Post savedPost = postService.save(post);

		return new ResponseEntity<>(
			modelMapper.map(savedPost, PostDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<PostDTO> edit(@RequestBody @Valid PostDTO post, @PathVariable Long id) {

		Post foundPost = postService.findOne(id);
		
		if (foundPost == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		foundPost.setDescription(post.getDescription());
		foundPost.setContent(post.getContent());

		Post persisted = postService.save(foundPost);

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
