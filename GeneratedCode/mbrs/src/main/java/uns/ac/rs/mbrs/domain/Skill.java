package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.JobOffer;
import uns.ac.rs.mbrs.domain.User;


@Table(name="skill")
@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="name", unique=false)
	private String name;

	@OneToMany(mappedBy="skill",cascade=CascadeType.REMOVE)
	private ArrayList<JobOffer> jobOffer;

	@OneToMany(mappedBy="skill",cascade=CascadeType.REMOVE)
	private ArrayList<User> user;

	public Skill(){}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getString() {
		return name;
	}
	
	public void setString(String name}) {
		this.name = name;
	}
	public JobOffer getJobOffer() {
		return jobOffer;
	}
	
	public void setJobOffer(JobOffer jobOffer}) {
		this.jobOffer = jobOffer;
	}
	public User getUser() {
		return user;
	}
	
	public void setUser(User user}) {
		this.user = user;
	}

}
