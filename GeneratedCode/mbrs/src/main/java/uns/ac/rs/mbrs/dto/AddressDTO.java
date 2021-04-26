package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.UserDTO;
import uns.ac.rs.mbrs.dto.CompanyDTO;
import uns.ac.rs.mbrs.dto.EducationInsitutionDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO{
	private Long id;
	
	@Column(name="street", unique=false)
	private String street
		@Column(name="city", unique=false)
	private String city
		@Column(name="country", unique=false)
	private String country
		@OneToMany(mappedBy="address",cascade=CascadeType.REMOVE)
	private User user
		@ManyToOne(fetch=FetchType.LAZY)
	private Company 
		@ManyToOne(fetch=FetchType.LAZY)
	private EducationInsitution 
	s
}