package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.Module;
import uns.ac.rs.mbrs.domain.Address;


@Table(name="educationInsitution")
@Entity
public class EducationInsitution {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="name", unique=false)
	private String name;

	@OneToMany(mappedBy="educationinsitution",cascade=CascadeType.REMOVE)
	private ArrayList<Module> module;

	@ManyToOne(fetch=FetchType.LAZY)
	private Address address;

	public EducationInsitution(){}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getString() {
		return name;
	}
	
	public void setString(String name}) {
		this.name = name;
	}
	public Module getModule() {
		return module;
	}
	
	public void setModule(Module module}) {
		this.module = module;
	}
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address}) {
		this.address = address;
	}

}
