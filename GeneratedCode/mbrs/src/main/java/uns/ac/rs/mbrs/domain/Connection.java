package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.ConnectionDTO;
import uns.ac.rs.mbrs.domain.User;
import java.util.ArrayList;
import java.util.List;


@Table(name="connection")
@Entity
public class Connection {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="active", unique=true)
	private Boolean active;

	@ManyToMany
@JoinTable(name="users",
	 joinColumns=@JoinColumn(name="connectionId"),
	 inverseJoinColumns=@JoinColumn(name="userId")
	)
	private List<User> user;

	public Connection(){}
	
	public Connection(ConnectionDTO connectionDTO){
		this.active = connectionDTO.getActive();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	public List<User> getUser() {
		return user;
	}

	public void setUser(ArrayList<User> user) {
		this.user = user;
	}

	

}
