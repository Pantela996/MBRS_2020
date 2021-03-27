package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;


@Table(name="job")
@Entity
public class Job {  

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id; 
	
	@Column(name="nameofposition", unique=false)
	private String nameOfPosition
	@OneToMany(mappedBy="job",cascade=CascadeType.REMOVE)
	private Experience experience
	@ManyToOne(fetch=FetchType.LAZY)
	private JobOffer 
	@ManyToOne(fetch=FetchType.LAZY)
	private Company company

	public Job(){}

	
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}


}
