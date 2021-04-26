package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.UserDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO{
	private Long id;
	
	@Column(name="description", unique=false)
	private String description
		@Column(name="content", unique=false)
	private String content
		@Column(name="createdate", unique=false)
	private date createDate
		@ManyToOne(fetch=FetchType.LAZY)
	private User user
	s
}