package uns.ac.rs.mbrs.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.User;
import uns.ac.rs.mbrs.dto.RegisterDTO;
import uns.ac.rs.mbrs.exceptions.EntityAlreadyExistsException;
import uns.ac.rs.mbrs.exceptions.InvalidDataException;
import uns.ac.rs.mbrs.repository.UserAuthRepository;
import uns.ac.rs.mbrs.repository.UserRepository;
import uns.ac.rs.mbrs.service.UserAuthService;

@Service
@Transactional
public class UserAuthServiceImpl implements UserAuthService {
	
	@Autowired
	private UserAuthRepository userRepository;

	@Override
	public void registerUser(RegisterDTO registerDTO) throws EntityAlreadyExistsException, InvalidDataException {
		if(registerDTO == null) {
			throw new InvalidDataException("Data is null");
		}
				
		User user = new User();
		
		if(registerDTO.getUsername() == null) {
			throw new InvalidDataException("Username is null");
		}
		if(registerDTO.getPassword() == null) {
			throw new InvalidDataException("Password is null");
		}
		if(registerDTO.getEmail() == null) {
			throw new InvalidDataException("Email is null");
		}
	
		if(userRepository.findByUsername(registerDTO.getUsername()) != null) {
			throw new EntityAlreadyExistsException("Username taken");
		}
		
		if(registerDTO.getUsername().length() < 3 || registerDTO.getUsername().length() > 20)
			throw new InvalidDataException("Username format");
		if(registerDTO.getPassword().length() < 3 || registerDTO.getPassword().length() > 20)
			throw new InvalidDataException("Password format");
		if(registerDTO.getEmail().length() < 3 || registerDTO.getEmail().length() > 20)
			throw new InvalidDataException("Email format");
		
		user.setUsername(registerDTO.getUsername());
		user.setPassword(registerDTO.getPassword());

		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		System.out.println(enc.encode(user.getPassword()));
		user.setPassword(enc.encode(user.getPassword()));
		userRepository.save(user);
		return;
	}
}
