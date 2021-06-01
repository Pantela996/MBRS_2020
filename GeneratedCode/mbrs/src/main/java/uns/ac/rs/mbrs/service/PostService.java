package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.Post;

import java.util.List;
import java.util.Date;

public interface PostService{

	Post findOne(Long id); 
	
	Post save(Post post);

	List<Post> findAll();

	Post remove(Long id);
	
}