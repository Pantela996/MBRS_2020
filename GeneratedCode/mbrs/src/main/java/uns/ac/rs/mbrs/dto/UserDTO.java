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
	
	@Column(name="username", unique=true)
	private String username
		@Column(name="password", unique=false)
	private String password
		@Column(name="firstname", unique=false)
	private String firstName
		@Column(name="lastname", unique=false)
	private String lastName
		@Column(name="summary", unique=false)
	private String summary
		@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Post post
		@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Connection connection
		@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Experience experience
		@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Address address
		@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Skill skills
		@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Education educations
	s
}