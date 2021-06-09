package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.ModuleDTO;
import uns.ac.rs.mbrs.dto.AddressDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationInstitutionDTO{
	private Long id;
	
	private ArrayList<ModuleDTO> module;
	private AddressDTO address;
	private String name;

	public ArrayList<ModuleDTO> getModule() {
		return module;
	}

	public void setModule(ArrayList<ModuleDTO> module) {
		this.module = module;
	}
	
	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}