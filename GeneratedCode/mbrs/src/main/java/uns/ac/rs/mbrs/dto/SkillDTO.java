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
	
	private String name;
	private JobOfferDTO jobOffer;
	private UserDTO user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JobOfferDTO getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOfferDTO jobOffer) {
		this.jobOffer = jobOffer;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}


}