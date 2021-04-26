package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionDTO{
	private Long id;
	
	@Column(name="topic", unique=false)
	private String topic
	s
}