package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.ExperienceDTO;
import uns.ac.rs.mbrs.dto.JobOfferDTO;
import uns.ac.rs.mbrs.dto.CompanyDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO{
	private Long id;
	
	private String nameOfPosition;

}