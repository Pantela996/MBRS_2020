package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.EducationDTO;
import uns.ac.rs.mbrs.dto.EducationInsitutionDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO{
	private Long id;
	
	@Column(name="name", unique=false)
	private String name
		@OneToMany(mappedBy="module",cascade=CascadeType.REMOVE)
	private Education education
		@ManyToOne(fetch=FetchType.LAZY)
	private EducationInsitution educationInstituion
	s
}