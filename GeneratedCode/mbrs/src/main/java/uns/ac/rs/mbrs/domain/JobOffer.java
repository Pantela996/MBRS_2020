package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.Job;
import uns.ac.rs.mbrs.domain.Skill;


@Table(name="jobOffer")
@Entity
public class JobOffer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id;


	@Column(name="expirationdate", unique=false)
	private date expirationDate;

	@ManyToOne(fetch=FetchType.LAZY)
	private Job ;

	@OneToMany(mappedBy="joboffer",cascade=CascadeType.REMOVE)
	private Skill skill;


	public JobOffer(){}


	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}


}
