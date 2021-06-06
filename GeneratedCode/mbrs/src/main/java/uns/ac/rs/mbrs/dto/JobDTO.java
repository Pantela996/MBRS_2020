package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.ExperienceDTO;
import uns.ac.rs.mbrs.dto.JobOfferDTO;
import uns.ac.rs.mbrs.dto.CompanyDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO{
	private Long id;
	
	private String nameOfPosition;
	private ArrayList<ExperienceDTO> experience;
	private JobOfferDTO jobOffer;
	private CompanyDTO company;

	public String getNameOfPosition() {
		return nameOfPosition;
	}

	public void setNameOfPosition(String nameOfPosition) {
		this.nameOfPosition = nameOfPosition;
	}

	public ArrayList<ExperienceDTO> getExperience() {
		return experience;
	}

	public void setExperience(ArrayList<ExperienceDTO> experience) {
		this.experience = experience;
	}
	
	public JobOfferDTO getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOfferDTO jobOffer) {
		this.jobOffer = jobOffer;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}


}