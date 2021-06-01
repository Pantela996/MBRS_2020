package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	Optional<Address> findById(Long id);
	
	List<Address> findAll();
}

