package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uns.ac.rs.mbrs.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	Company findById(Long id);
}

