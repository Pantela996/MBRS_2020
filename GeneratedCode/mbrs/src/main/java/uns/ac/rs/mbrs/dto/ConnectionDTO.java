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
public class ConnectionDTO{
	private Long id;
	
	@Column(name="active", unique=false)
	private Boolean active
		@OneToMany(mappedBy="connection",cascade=CascadeType.REMOVE)
	private User user
	s
}