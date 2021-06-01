package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.Discussion;
import uns.ac.rs.mbrs.repository.DiscussionRepository;
import uns.ac.rs.mbrs.service.DiscussionService;

import java.util.Date;


@Service
@Transactional
public class DiscussionServiceImpl implements DiscussionService {

	@Autowired
	private DiscussionRepository discussionRepository;
	
	
	@Override
	public Discussion findOne(Long id) {
		return discussionRepository.findById(id).get();
	}

	@Override
	public List<Discussion> findAll() {
		return discussionRepository.findAll();
	}

	@Override
	public Discussion save(Discussion discussion) {
		return discussionRepository.save(discussion);
	}
	
	@Override
	public Discussion remove(Long id) {
		Discussion discussion = discussionRepository.findById(id).get();
		if(discussion == null){
			throw new IllegalArgumentException("Tried to delete :( Discussion");
		}
		discussionRepository.delete(discussion);
		return discussion;
	}
	
}