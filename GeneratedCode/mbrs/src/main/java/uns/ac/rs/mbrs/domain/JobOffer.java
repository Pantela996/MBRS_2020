package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.JobOfferDTO;
import uns.ac.rs.mbrs.domain.Job;
import uns.ac.rs.mbrs.domain.Skill;
import java.util.ArrayList;
import java.util.List;


@Table(name="jobOffer")
@Entity
public class JobOffer extends Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="expirationdate", unique=true)
	private Date expirationDate;

	@OneToOne
	private Job job;

	@ManyToMany
@JoinTable(name="skills",
	 joinColumns=@JoinColumn(name="jobofferId"),
	 inverseJoinColumns=@JoinColumn(name="skillId")
	)
	private List<Skill> skill;

	public JobOffer(){}
	
	public JobOffer(JobOfferDTO jobOfferDTO){
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	
	public List<Skill> getSkill() {
		return skill;
	}

	public void setSkill(ArrayList<Skill> skill) {
		this.skill = skill;
	}

	

}
