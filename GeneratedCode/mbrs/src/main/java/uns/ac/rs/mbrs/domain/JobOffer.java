package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.JobOfferDTO;
import uns.ac.rs.mbrs.domain.Job;
import uns.ac.rs.mbrs.domain.Skill;
import java.util.ArrayList;


@Table(name="jobOffer")
@Entity
public class JobOffer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="expirationdate", unique=false)
	private Date expirationDate;

	@ManyToOne(fetch=FetchType.LAZY)
	private Job job;

	@OneToMany(mappedBy="joboffer",cascade=CascadeType.REMOVE)
	private ArrayList<Skill> skill;

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

	
	public ArrayList<Skill> getSkill() {
		return skill;
	}

	public void setSkill(ArrayList<Skill> skill) {
		this.skill = skill;
	}

	

}
