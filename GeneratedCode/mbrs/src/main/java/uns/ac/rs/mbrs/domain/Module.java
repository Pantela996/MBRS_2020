package uns.ac.rs.mbrs.domain;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.ModuleDTO;
import uns.ac.rs.mbrs.domain.Education;
import java.util.ArrayList;
import java.util.List;
import uns.ac.rs.mbrs.domain.EducationInstitution;


@Table(name="module")
@Entity
public class Module  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Column(name="name", unique=true)
	private String name;

	@OneToMany(mappedBy="module",cascade=CascadeType.REMOVE)
	private List<Education> education;

	@ManyToOne(fetch=FetchType.LAZY)
	private EducationInstitution educationInstitution;

	public Module(){}
	
	public Module(ModuleDTO moduleDTO){
		this.name = moduleDTO.getName();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(ArrayList<Education> education) {
		this.education = education;
	}

	
	public EducationInstitution getEducationInstitution() {
		return educationInstitution;
	}

	public void setEducationInstitution(EducationInstitution educationInstitution) {
		this.educationInstitution = educationInstitution;
	}

	

}
