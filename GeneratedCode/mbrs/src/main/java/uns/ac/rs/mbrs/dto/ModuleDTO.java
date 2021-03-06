package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.EducationDTO;
import uns.ac.rs.mbrs.dto.EducationInstitutionDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO{
	private Long id;
	
	private String name;
	private ArrayList<EducationDTO> education;
	private EducationInstitutionDTO educationInstitution;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<EducationDTO> getEducation() {
		return education;
	}

	public void setEducation(ArrayList<EducationDTO> education) {
		this.education = education;
	}
	
	public EducationInstitutionDTO getEducationInstitution() {
		return educationInstitution;
	}

	public void setEducationInstitution(EducationInstitutionDTO educationInstitution) {
		this.educationInstitution = educationInstitution;
	}


}