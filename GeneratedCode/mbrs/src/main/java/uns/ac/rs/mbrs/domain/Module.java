package uns.ac.rs.mbrs.domain;
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.ModuleDTO;
import uns.ac.rs.mbrs.domain.Education;
import java.util.ArrayList;
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

	
	public ArrayList<Education> getEducation() {
		return education;
	}

	public void setEducation(ArrayList<Education> education) {
		this.education = education;
	}

	
	public EducationInsitution getEducationInstituion() {
		return educationInstituion;
	}

	public void setEducationInstituion(EducationInsitution educationInstituion) {
		this.educationInstituion = educationInstituion;
	}

	

}
