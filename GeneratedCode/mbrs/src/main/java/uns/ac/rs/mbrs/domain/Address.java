package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.User;
import uns.ac.rs.mbrs.domain.Company;
import uns.ac.rs.mbrs.domain.EducationInsitution;


@Table(name="address")
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="street", unique=false)
	private String street;

	@Column(name="city", unique=false)
	private String city;

	@Column(name="country", unique=false)
	private String country;

	@OneToMany(mappedBy="address",cascade=CascadeType.REMOVE)
	private ArrayList<User> user;

	@ManyToOne(fetch=FetchType.LAZY)
	private Company company;

	@ManyToOne(fetch=FetchType.LAZY)
	private EducationInsitution educationInstitution;

	public Address(){}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getString() {
		return street;
	}
	
	public void setString(String street}) {
		this.street = street;
	}
	public String getString() {
		return city;
	}
	
	public void setString(String city}) {
		this.city = city;
	}
	public String getString() {
		return country;
	}
	
	public void setString(String country}) {
		this.country = country;
	}
	public User getUser() {
		return user;
	}
	
	public void setUser(User user}) {
		this.user = user;
	}
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company}) {
		this.company = company;
	}
	public EducationInsitution getEducationInsitution() {
		return educationInstitution;
	}
	
	public void setEducationInsitution(EducationInsitution educationInstitution}) {
		this.educationInstitution = educationInstitution;
	}

}
