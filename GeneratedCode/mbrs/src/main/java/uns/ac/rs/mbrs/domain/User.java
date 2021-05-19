package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.Post;
import uns.ac.rs.mbrs.domain.Connection;
import uns.ac.rs.mbrs.domain.Experience;
import uns.ac.rs.mbrs.domain.Address;
import uns.ac.rs.mbrs.domain.Skill;
import uns.ac.rs.mbrs.domain.Education;


@Table(name="user")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="username", unique=true)
	private String username;

	@Column(name="password", unique=false)
	private String password;

	@Column(name="firstname", unique=false)
	private String firstName;

	@Column(name="lastname", unique=false)
	private String lastName;

	@Column(name="summary", unique=false)
	private String summary;

	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private ArrayList<Post> posts;

	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private ArrayList<Connection> connection;

	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private ArrayList<Experience> experience;

	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private ArrayList<Address> address;

	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private ArrayList<Skill> skills;

	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private ArrayList<Education> educations;

	public User(){}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getString() {
		return username;
	}
	
	public void setString(String username}) {
		this.username = username;
	}
	public String getString() {
		return password;
	}
	
	public void setString(String password}) {
		this.password = password;
	}
	public String getString() {
		return firstName;
	}
	
	public void setString(String firstName}) {
		this.firstName = firstName;
	}
	public String getString() {
		return lastName;
	}
	
	public void setString(String lastName}) {
		this.lastName = lastName;
	}
	public String getString() {
		return summary;
	}
	
	public void setString(String summary}) {
		this.summary = summary;
	}
	public Post getPost() {
		return posts;
	}
	
	public void setPost(Post posts}) {
		this.posts = posts;
	}
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection}) {
		this.connection = connection;
	}
	public Experience getExperience() {
		return experience;
	}
	
	public void setExperience(Experience experience}) {
		this.experience = experience;
	}
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address}) {
		this.address = address;
	}
	public Skill getSkill() {
		return skills;
	}
	
	public void setSkill(Skill skills}) {
		this.skills = skills;
	}
	public Education getEducation() {
		return educations;
	}
	
	public void setEducation(Education educations}) {
		this.educations = educations;
	}

}
