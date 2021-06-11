package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.dto.RegisterDTO;
import uns.ac.rs.mbrs.exceptions.EntityAlreadyExistsException;
import uns.ac.rs.mbrs.exceptions.InvalidDataException;

public interface UserAuthService {
	void registerUser(RegisterDTO registerDto) throws EntityAlreadyExistsException, InvalidDataException ;
}
