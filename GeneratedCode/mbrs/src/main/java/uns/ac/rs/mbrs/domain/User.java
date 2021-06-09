package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.UserDTO;
import uns.ac.rs.mbrs.domain.Post;
import java.util.ArrayList;
import java.util.List;
import uns.ac.rs.mbrs.domain.Connection;
import uns.ac.rs.mbrs.domain.Experience;
import uns.ac.rs.mbrs.domain.Address;
import uns.ac.rs.mbrs.domain.Skill;
import uns.ac.rs.mbrs.domain.Education;


@Table(name="user")
@Entity
public class User  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="username", unique=true)
	private String username;

	@Column(name="password", unique=true)
	private String password;

	@Column(name="firstname", unique=true)
	private String firstName;

	@Column(name="lastname", unique=true)
	private String lastName;

	@Column(name="summary", unique=true)
	private String summary;

	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private List<Post> posts;

	@ManyToMany
@JoinTable(name="connections",
	 joinColumns=@JoinColumn(name="userId"),
	 inverseJoinColumns=@JoinColumn(name="connectionId")
	)
	private List<Connection> connection;

	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private List<Experience> experience;

	@ManyToMany
@JoinTable(name="addresss",
	 joinColumns=@JoinColumn(name="userId"),
	 inverseJoinColumns=@JoinColumn(name="addressId")
	)
	private List<Address> address;

	@ManyToMany
@JoinTable(name="skillss",
	 joinColumns=@JoinColumn(name="userId"),
	 inverseJoinColumns=@JoinColumn(name="skillsId")
	)
	private List<Skill> skills;

	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
	private List<Education> educations;

	public User(){}
	
	public User(UserDTO userDTO){
		this.username = userDTO.getUsername();
		this.password = userDTO.getPassword();
		this.firstName = userDTO.getFirstName();
		this.lastName = userDTO.getLastName();
		this.summary = userDTO.getSummary();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	
	public List<Connection> getConnection() {
		return connection;
	}

	public void setConnection(ArrayList<Connection> connection) {
		this.connection = connection;
	}

	
	public List<Experience> getExperience() {
		return experience;
	}

	public void setExperience(ArrayList<Experience> experience) {
		this.experience = experience;
	}

	
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<Address> address) {
		this.address = address;
	}

	
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}

	
	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(ArrayList<Education> educations) {
		this.educations = educations;
	}

	

}
