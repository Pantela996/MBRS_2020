package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.AddressDTO;
import uns.ac.rs.mbrs.dto.JobDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO{
	private Long id;
	
	private String name;
	private AddressDTO address;
	private JobDTO job;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public JobDTO getJob() {
		return job;
	}

	public void setJob(JobDTO job) {
		this.job = job;
	}


}