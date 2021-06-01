package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.Module;
import uns.ac.rs.mbrs.domain.User;


@Table(name="education")
@Entity
public class Education {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="startdate", unique=false)
	private date startDate;

	@Column(name="enddate", unique=false)
	private date endDate;

	@ManyToOne(fetch=FetchType.LAZY)
	private Module module;

	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	public Education(){}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public date getStartDate() {
		return startDate;
	}

	public void setStartDate(date startDate) {
		this.startDate = startDate;
	}

	
	public date getEndDate() {
		return endDate;
	}

	public void setEndDate(date endDate) {
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
