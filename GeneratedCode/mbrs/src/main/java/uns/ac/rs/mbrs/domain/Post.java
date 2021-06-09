package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.PostDTO;
import uns.ac.rs.mbrs.domain.User;


@Table(name="post")
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="description", unique=true)
	private String description;

	@Column(name="content", unique=true)
	private String content;

	@Column(name="createdate", unique=true)
	private Date createDate;

	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	public Post(){}
	
	public Post(PostDTO postDTO){
		this.description = postDTO.getDescription();
		this.content = postDTO.getContent();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
