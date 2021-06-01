package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.Connection;
import uns.ac.rs.mbrs.repository.ConnectionRepository;
import uns.ac.rs.mbrs.service.ConnectionService;

import java.util.Date;


@Service
@Transactional
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	private ConnectionRepository connectionRepository;
	
	
	@Override
	public Connection findOne(Long id) {
		return connectionRepository.findById(id).get();
	}

	@Override
	public List<Connection> findAll() {
		return connectionRepository.findAll();
	}

	@Override
	public Connection save(Connection connection) {
		return connectionRepository.save(connection);
	}
	
	@Override
	public Connection remove(Long id) {
		Connection connection = connectionRepository.findById(id).get();
		if(connection == null){
			throw new IllegalArgumentException("Tried to delete :( Connection");
		}
		connectionRepository.delete(connection);
		return connection;
	}
	
}