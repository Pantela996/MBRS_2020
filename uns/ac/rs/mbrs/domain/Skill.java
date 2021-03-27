package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;


@Table(name="skill")
@Entity
public class Skill {  

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id; 
	
	@Column(name="name", unique=false)
	private String name
	@OneToMany(mappedBy="skill",cascade=CascadeType.REMOVE)
	private JobOffer jobOffer
	@OneToMany(mappedBy="skill",cascade=CascadeType.REMOVE)
	private User user

	public Skill(){}

	
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}


}
