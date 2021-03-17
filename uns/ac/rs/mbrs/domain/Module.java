package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;


@Table(name="module")
@Entity
public class Module {  

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
		
	public Module(){}

	
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}


}
