package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.UserDTO;
import uns.ac.rs.mbrs.dto.JobDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDTO{
	private Long id;
	
	@Column(name="startdate", unique=false)
	private date startDate
		@Column(name="enddate", unique=false)
	private date endDate
		@ManyToOne(fetch=FetchType.LAZY)
	private User user
		@ManyToOne(fetch=FetchType.LAZY)
	private Job job
	s
}