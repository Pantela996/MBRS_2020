package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.PostDTO;
import uns.ac.rs.mbrs.dto.ConnectionDTO;
import uns.ac.rs.mbrs.dto.ExperienceDTO;
import uns.ac.rs.mbrs.dto.AddressDTO;
import uns.ac.rs.mbrs.dto.SkillDTO;
import uns.ac.rs.mbrs.dto.EducationDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{
	private Long id;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String summary;

}