package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.UserDTO;
import uns.ac.rs.mbrs.dto.CompanyDTO;
import uns.ac.rs.mbrs.dto.EducationInstitutionDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO{
	private Long id;
	
	private String street;
	private String city;
	private String country;
	private UserDTO user;
	private CompanyDTO company;
	private EducationInstitutionDTO educationInstitution;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	public EducationInstitutionDTO getEducationInstitution() {
		return educationInstitution;
	}

	public void setEducationInstitution(EducationInstitutionDTO educationInstitution) {
		this.educationInstitution = educationInstitution;
	}


}