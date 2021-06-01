package uns.ac.rs.mbrs.service;

import uns.ac.rs.mbrs.domain.Company;

import java.util.List;
import java.util.Date;

public interface CompanyService{

	Company findOne(Long id); 
	
	Company save(Company company);

	List<Company> findAll();

	Company remove(Long id);
	
}