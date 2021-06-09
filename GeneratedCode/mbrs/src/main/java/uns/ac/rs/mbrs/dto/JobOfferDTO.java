package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.JobDTO;
import uns.ac.rs.mbrs.dto.SkillDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferDTO{
	private Long id;
	
	private Date expirationDate;
	private JobDTO job;
	private SkillDTO skill;

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public JobDTO getJob() {
		return job;
	}

	public void setJob(JobDTO job) {
		this.job = job;
	}

	public SkillDTO getSkill() {
		return skill;
	}

	public void setSkill(SkillDTO skill) {
		this.skill = skill;
	}


}