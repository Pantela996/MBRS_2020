package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.JobDTO;
import uns.ac.rs.mbrs.dto.SkillDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferDTO{
	private Long id;
	
	@Column(name="expirationdate", unique=false)
	private date expirationDate
		@ManyToOne(fetch=FetchType.LAZY)
	private Job 
		@OneToMany(mappedBy="joboffer",cascade=CascadeType.REMOVE)
	private Skill skill
	s
}