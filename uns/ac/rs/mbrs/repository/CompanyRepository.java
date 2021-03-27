package uns.ac.rs.mbrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	Company findById(Long id);
}