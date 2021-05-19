package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.JobOfferDTO;
import uns.ac.rs.mbrs.dto.UserDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkillDTO{
	private Long id;
	
	@Column(name="name", unique=false)
	private String name
		@OneToMany(mappedBy="skill",cascade=CascadeType.REMOVE)
	private JobOffer jobOffer
		@OneToMany(mappedBy="skill",cascade=CascadeType.REMOVE)
	private User user
	s
}