package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	Address findById(Long id);
}