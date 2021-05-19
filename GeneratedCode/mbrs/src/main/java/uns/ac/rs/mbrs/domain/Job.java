package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.Experience;
import uns.ac.rs.mbrs.domain.JobOffer;
import uns.ac.rs.mbrs.domain.Company;


@Table(name="job")
@Entity
public class Job {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="nameofposition", unique=false)
	private String nameOfPosition;

	@OneToMany(mappedBy="job",cascade=CascadeType.REMOVE)
	private ArrayList<Experience> experience;

	@ManyToOne(fetch=FetchType.LAZY)
	private JobOffer jobOffer;

	@ManyToOne(fetch=FetchType.LAZY)
	private Company company;

	public Job(){}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getString() {
		return nameOfPosition;
	}
	
	public void setString(String nameOfPosition}) {
		this.nameOfPosition = nameOfPosition;
	}
	public Experience getExperience() {
		return experience;
	}
	
	public void setExperience(Experience experience}) {
		this.experience = experience;
	}
	public JobOffer getJobOffer() {
		return jobOffer;
	}
	
	public void setJobOffer(JobOffer jobOffer}) {
		this.jobOffer = jobOffer;
	}
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company}) {
		this.company = company;
	}

}
