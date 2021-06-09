package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.EducationDTO;
import uns.ac.rs.mbrs.domain.Module;
import uns.ac.rs.mbrs.domain.User;


@Table(name="education")
@Entity
public class Education {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="startdate", unique=true)
	private Date startDate;

	@Column(name="enddate", unique=true)
	private Date endDate;

	@ManyToOne(fetch=FetchType.LAZY)
	private Module module;

	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	public Education(){}
	
	public Education(EducationDTO educationDTO){
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

	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
