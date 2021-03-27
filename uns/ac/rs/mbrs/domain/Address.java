package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;


@Table(name="address")
@Entity
public class Address {  

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id; 
	
	@Column(name="street", unique=false)
	private String street
	@Column(name="city", unique=false)
	private String city
	@Column(name="country", unique=false)
	private String country
	@OneToMany(mappedBy="address",cascade=CascadeType.REMOVE)
	private User user
	@ManyToOne(fetch=FetchType.LAZY)
	private Company 
	@ManyToOne(fetch=FetchType.LAZY)
	private EducationInsitution 

	public Address(){}

	
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}


}
