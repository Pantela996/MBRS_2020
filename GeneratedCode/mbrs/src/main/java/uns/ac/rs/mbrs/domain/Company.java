package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.CompanyDTO;
import uns.ac.rs.mbrs.domain.Address;
import uns.ac.rs.mbrs.domain.Job;


@Table(name="company")
@Entity
public class Company  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="name", unique=true)
	private String name;

	@OneToOne
	private Address address;

	@OneToOne
	private Job job;

	public Company(){}
	
	public Company(CompanyDTO companyDTO){
		this.name = companyDTO.getName();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	

}
