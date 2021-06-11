package uns.ac.rs.mbrs.repository;

import uns.ac.rs.mbrs.domain.User;

public interface UserAuthRepository extends UserRepository {
	User findByUsername(String username);

}
