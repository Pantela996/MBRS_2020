package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.Job;
import uns.ac.rs.mbrs.domain.Skill;


@Table(name="jobOffer")
@Entity
public class JobOffer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="expirationdate", unique=false)
	private date expirationDate;

	@ManyToOne(fetch=FetchType.LAZY)
	private Job job;

	@OneToMany(mappedBy="joboffer",cascade=CascadeType.REMOVE)
	private ArrayList<Skill> skill;

	public JobOffer(){}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public date getdate() {
		return expirationDate;
	}
	
	public void setdate(date expirationDate}) {
		this.expirationDate = expirationDate;
	}
	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job}) {
		this.job = job;
	}
	public Skill getSkill() {
		return skill;
	}
	
	public void setSkill(Skill skill}) {
		this.skill = skill;
	}

}
