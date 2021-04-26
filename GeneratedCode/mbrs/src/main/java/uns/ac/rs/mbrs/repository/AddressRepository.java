package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	Address findById(Long id);
}

