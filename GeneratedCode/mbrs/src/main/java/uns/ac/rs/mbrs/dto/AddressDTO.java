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

}