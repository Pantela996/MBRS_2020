package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.ExperienceDTO;
import uns.ac.rs.mbrs.domain.User;
import uns.ac.rs.mbrs.domain.Job;


@Table(name="experience")
@Entity
public class Experience  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="startdate", unique=true)
	private Date startDate;

	@Column(name="enddate", unique=true)
	private Date endDate;

	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	@ManyToOne(fetch=FetchType.LAZY)
	private Job job;

	public Experience(){}
	
	public Experience(ExperienceDTO experienceDTO){
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	

}
