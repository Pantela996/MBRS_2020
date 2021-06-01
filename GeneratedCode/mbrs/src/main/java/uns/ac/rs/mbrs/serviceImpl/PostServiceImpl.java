package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.Post;
import uns.ac.rs.mbrs.repository.PostRepository;
import uns.ac.rs.mbrs.service.PostService;

import java.util.Date;


@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public Post findOne(Long id) {
		return postRepository.findById(id).get();
	}

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}
	
	@Override
	public Post remove(Long id) {
		Post post = postRepository.findById(id).get();
		if(post == null){
			throw new IllegalArgumentException("Tried to delete :( Post");
		}
		postRepository.delete(post);
		return post;
	}
	
}