package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.User;


@Table(name="connection")
@Entity
public class Connection {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="active", unique=false)
	private Boolean active;

	@OneToMany(mappedBy="connection",cascade=CascadeType.REMOVE)
	private ArrayList<User> user;

	public Connection(){}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Boolean getBoolean() {
		return active;
	}
	
	public void setBoolean(Boolean active}) {
		this.active = active;
	}
	public User getUser() {
		return user;
	}
	
	public void setUser(User user}) {
		this.user = user;
	}

}
