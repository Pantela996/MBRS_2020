package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.EducationInstitutionDTO;
import uns.ac.rs.mbrs.domain.Module;
import java.util.ArrayList;
import java.util.List;
import uns.ac.rs.mbrs.domain.Address;


@Table(name="educationInstitution")
@Entity
public class EducationInstitution  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@OneToMany(mappedBy="educationInstitution",cascade=CascadeType.REMOVE)
	private List<Module> module;

	@OneToOne
	private Address address;

	@Column(name="name", unique=true)
	private String name;

	public EducationInstitution(){}
	
	public EducationInstitution(EducationInstitutionDTO educationInstitutionDTO){
		this.name = educationInstitutionDTO.getName();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public List<Module> getModule() {
		return module;
	}

	public void setModule(ArrayList<Module> module) {
		this.module = module;
	}

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
