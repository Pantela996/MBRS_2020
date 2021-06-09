package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.JobDTO;
import uns.ac.rs.mbrs.domain.Experience;
import java.util.ArrayList;
import java.util.List;
import uns.ac.rs.mbrs.domain.JobOffer;
import uns.ac.rs.mbrs.domain.Company;


@Table(name="job")
@Entity
public class Job  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="nameofposition", unique=true)
	private String nameOfPosition;

	@OneToMany(mappedBy="job",cascade=CascadeType.REMOVE)
	private List<Experience> experience;

	@OneToOne
	private JobOffer jobOffer;

	@OneToOne
	private Company company;

	public Job(){}
	
	public Job(JobDTO jobDTO){
		this.nameOfPosition = jobDTO.getNameOfPosition();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getNameOfPosition() {
		return nameOfPosition;
	}

	public void setNameOfPosition(String nameOfPosition) {
		this.nameOfPosition = nameOfPosition;
	}

	
	public List<Experience> getExperience() {
		return experience;
	}

	public void setExperience(ArrayList<Experience> experience) {
		this.experience = experience;
	}

	
	public JobOffer getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(JobOffer jobOffer) {
		this.jobOffer = jobOffer;
	}

	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	

}
