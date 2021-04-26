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
	private User ;


	public Education(){}


	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}


}
