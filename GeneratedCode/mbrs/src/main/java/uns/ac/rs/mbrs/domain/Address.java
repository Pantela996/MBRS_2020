package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.AddressDTO;
import uns.ac.rs.mbrs.domain.User;
import java.util.ArrayList;
import java.util.List;
import uns.ac.rs.mbrs.domain.Company;
import uns.ac.rs.mbrs.domain.EducationInstitution;


@Table(name="address")
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="street", unique=true)
	private String street;

	@Column(name="city", unique=true)
	private String city;

	@Column(name="country", unique=true)
	private String country;

	@ManyToMany
@JoinTable(name="users",
	 joinColumns=@JoinColumn(name="addressId"),
	 inverseJoinColumns=@JoinColumn(name="userId")
	)
	private List<User> user;

	@OneToOne
	private Company company;

	@OneToOne
	private EducationInstitution educationInstitution;

	public Address(){}
	
	public Address(AddressDTO addressDTO){
		this.street = addressDTO.getStreet();
		this.city = addressDTO.getCity();
		this.country = addressDTO.getCountry();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	public List<User> getUser() {
		return user;
	}

	public void setUser(ArrayList<User> user) {
		this.user = user;
	}

	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	
	public EducationInstitution getEducationInstitution() {
		return educationInstitution;
	}

	public void setEducationInstitution(EducationInstitution educationInstitution) {
		this.educationInstitution = educationInstitution;
	}

	

}
