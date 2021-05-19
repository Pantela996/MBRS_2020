package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.User;
import uns.ac.rs.mbrs.domain.Job;


@Table(name="experience")
@Entity
public class Experience {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="startdate", unique=false)
	private date startDate;

	@Column(name="enddate", unique=false)
	private date endDate;

	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	@ManyToOne(fetch=FetchType.LAZY)
	private Job job;

	public Experience(){}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public date getdate() {
		return startDate;
	}
	
	public void setdate(date startDate}) {
		this.startDate = startDate;
	}
	public date getdate() {
		return endDate;
	}
	
	public void setdate(date endDate}) {
		this.endDate = endDate;
	}
	public User getUser() {
		return user;
	}
	
	public void setUser(User user}) {
		this.user = user;
	}
	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job}) {
		this.job = job;
	}

}
