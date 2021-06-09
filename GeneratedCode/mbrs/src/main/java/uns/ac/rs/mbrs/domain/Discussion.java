package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.DiscussionDTO;


@Table(name="discussion")
@Entity
public class Discussion extends Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="topic", unique=true)
	private String topic;

	public Discussion(){}
	
	public Discussion(DiscussionDTO discussionDTO){
		this.topic = discussionDTO.getTopic();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	

}
