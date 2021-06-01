package uns.ac.rs.mbrs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	Optional<Company> findById(Long id);
	
	List<Company> findAll();
}

