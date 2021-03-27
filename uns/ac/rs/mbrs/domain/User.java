package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;


@Table(name="user")
@Entity
public class User {  

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id; 
	
	@Column(name="username", unique=true)
	private String username
	@Column(name="password", unique=false)
	private String password
	@Column(name="firstname", unique=false)
	private String firstName
	@Column(name="lastname", unique=false)
	private String lastName
	@Column(name="summary", unique=false)
	private String summary
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Post post
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Connection connection
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Experience experience
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Address address
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Skill skills
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private Education educations

	public User(){}

	
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}


}
