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
	
	private Boolean active;
	private ArrayList<UserDTO> user;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public ArrayList<UserDTO> getUser() {
		return user;
	}

	public void setUser(ArrayList<UserDTO> user) {
		this.user = user;
	}
	

}