package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.domain.Education;
import uns.ac.rs.mbrs.domain.EducationInsitution;


@Table(name="module")
@Entity
public class Module {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="name", unique=false)
	private String name;

	@OneToMany(mappedBy="module",cascade=CascadeType.REMOVE)
	private ArrayList<Education> education;

	@ManyToOne(fetch=FetchType.LAZY)
	private EducationInsitution educationInstituion;

	public Module(){}

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
	public Education getEducation() {
		return education;
	}
	
	public void setEducation(Education education}) {
		this.education = education;
	}
	public EducationInsitution getEducationInsitution() {
		return educationInstituion;
	}
	
	public void setEducationInsitution(EducationInsitution educationInstituion}) {
		this.educationInstituion = educationInstituion;
	}

}
