package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.Address;
import uns.ac.rs.mbrs.domain.Job;


@Table(name="company")
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="name", unique=false)
	private String name;

	@ManyToOne(fetch=FetchType.LAZY)
	private Address address;

	@ManyToOne(fetch=FetchType.LAZY)
	private Job job;

	public Company(){}

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
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address}) {
		this.address = address;
	}
	public Job getJob() {
		return job;
	}
	
	public void setJob(Job job}) {
		this.job = job;
	}

}
