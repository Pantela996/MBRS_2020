package uns.ac.rs.mbrs.controller;

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;


import uns.ac.rs.mbrs.model.Company;
import uns.ac.rs.mbrs.service.CompanyService;
import uns.ac.rs.mbrs.dto.CompanyDTO;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;


@RestController
@RequestMapping(value="/api/company")
public class CompanyController {  

	@Autowired
	private CompanyService companyService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<CompanyDTO>> getCompanyList () {

		List<Company> companyList = companyService.findAll();
		
		List<CompanyDTO> companyDTOList =
			companyList
			.stream()
            .map((element) -> modelMapper.map(element, CompanyDTO.class))
            .collect(Collectors.toList())
			
		return new ResponseEntity<>(
			companyDTOList,
			HttpStatus.OK
		);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<CompanyDTO> getCompany(@PathVariable Long id) {
		Company company = companyService.findOne(id);
		if (company == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
			modelMapper.map(company, CompanyDTO.class), 
			HttpStatus.OK
		);
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<CompanyDTO> add(@RequestBody @Valid CompanyDTO newCompany) {

		Company savedCompany = companyService.save(toCompany.convert(newCompany));

		return new ResponseEntity<>(
			modelMapper.map(savedCompany, CompanyDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<CompanyDTO> edit(@RequestBody @Valid CompanyDTO company, @PathVariable Long id) {

		if (id != company.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Company persisted = companyService.save(toCompany.convert(company));

		return new ResponseEntity<>(
			modelMapper.map(persisted, CompanyDTO.class),
			HttpStatus.CREATED
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<CompanyDTO> delete(@PathVariable Long id) {
		companyService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
