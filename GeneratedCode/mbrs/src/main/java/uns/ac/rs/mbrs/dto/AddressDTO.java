package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.UserDTO;
import uns.ac.rs.mbrs.dto.CompanyDTO;
import uns.ac.rs.mbrs.dto.EducationInsitutionDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO{
	private Long id;
	
	private String street;
	private String city;
	private String country;
	private ArrayList<UserDTO> user;
	private CompanyDTO company;
	private EducationInsitutionDTO educationInstitution;

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

	public ArrayList<UserDTO> getUser() {
		return user;
	}

	public void setUser(ArrayList<UserDTO> user) {
		this.user = user;
	}
	
	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	public EducationInsitutionDTO getEducationInstitution() {
		return educationInstitution;
	}

	public void setEducationInstitution(EducationInsitutionDTO educationInstitution) {
		this.educationInstitution = educationInstitution;
	}


}