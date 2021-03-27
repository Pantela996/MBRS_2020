package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;


@Table(name="post")
@Entity
public class Post {  

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id; 
	
	@Column(name="description", unique=false)
	private String description
	@Column(name="content", unique=false)
	private String content
	@Column(name="createdate", unique=false)
	private date createDate
	@ManyToOne(fetch=FetchType.LAZY)
	private User user

	public Post(){}

	
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}


}
