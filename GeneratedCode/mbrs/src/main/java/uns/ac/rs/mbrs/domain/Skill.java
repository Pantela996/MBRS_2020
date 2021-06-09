package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.SkillDTO;
import uns.ac.rs.mbrs.domain.JobOffer;
import java.util.ArrayList;
import java.util.List;
import uns.ac.rs.mbrs.domain.User;


@Table(name="skill")
@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="name", unique=true)
	private String name;

	@ManyToMany
@JoinTable(name="jobOffers",
	 joinColumns=@JoinColumn(name="skillId"),
	 inverseJoinColumns=@JoinColumn(name="jobOfferId")
	)
	private List<JobOffer> jobOffer;

	@ManyToMany
@JoinTable(name="users",
	 joinColumns=@JoinColumn(name="skillId"),
	 inverseJoinColumns=@JoinColumn(name="userId")
	)
	private List<User> user;

	public Skill(){}
	
	public Skill(SkillDTO skillDTO){
		this.name = skillDTO.getName();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<JobOffer> getJobOffer() {
		return jobOffer;
	}

	public void setJobOffer(ArrayList<JobOffer> jobOffer) {
		this.jobOffer = jobOffer;
	}

	
	public List<User> getUser() {
		return user;
	}

	public void setUser(ArrayList<User> user) {
		this.user = user;
	}

	

}
