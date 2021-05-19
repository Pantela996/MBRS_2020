package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.ModuleDTO;
import uns.ac.rs.mbrs.dto.UserDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EducationDTO{
	private Long id;
	
	@Column(name="startdate", unique=false)
	private date startDate
		@Column(name="enddate", unique=false)
	private date endDate
		@ManyToOne(fetch=FetchType.LAZY)
	private Module module
		@ManyToOne(fetch=FetchType.LAZY)
	private User user
	s
}