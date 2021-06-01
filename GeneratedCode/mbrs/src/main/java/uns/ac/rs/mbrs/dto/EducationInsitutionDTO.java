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
public class EducationInsitutionDTO{
	private Long id;
	
	private String name;

}