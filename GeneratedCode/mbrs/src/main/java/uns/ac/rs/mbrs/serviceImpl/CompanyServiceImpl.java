package uns.ac.rs.mbrs.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.domain.Company;
import uns.ac.rs.mbrs.repository.CompanyRepository;
import uns.ac.rs.mbrs.service.CompanyService;

import java.util.Date;


@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	
	@Override
	public Company findOne(Long id) {
		return companyRepository.findById(id).get();
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Company save(Company company) {
		return companyRepository.save(company);
	}
	
	@Override
	public Company remove(Long id) {
		Company company = companyRepository.findById(id).get();
		if(company == null){
			throw new IllegalArgumentException("Tried to delete :( Company");
		}
		companyRepository.delete(company);
		return company;
	}
	
}