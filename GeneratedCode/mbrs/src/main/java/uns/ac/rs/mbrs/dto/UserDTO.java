package uns.ac.rs.mbrs.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
import uns.ac.rs.mbrs.dto.PostDTO;
import uns.ac.rs.mbrs.dto.ConnectionDTO;
import uns.ac.rs.mbrs.dto.ExperienceDTO;
import uns.ac.rs.mbrs.dto.AddressDTO;
import uns.ac.rs.mbrs.dto.SkillDTO;
import uns.ac.rs.mbrs.dto.EducationDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO{
	private Long id;
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String summary;
	private ArrayList<PostDTO> posts;
	private ArrayList<ConnectionDTO> connection;
	private ArrayList<ExperienceDTO> experience;
	private ArrayList<AddressDTO> address;
	private ArrayList<SkillDTO> skills;
	private ArrayList<EducationDTO> educations;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public ArrayList<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<PostDTO> posts) {
		this.posts = posts;
	}
	
	public ArrayList<ConnectionDTO> getConnection() {
		return connection;
	}

	public void setConnection(ArrayList<ConnectionDTO> connection) {
		this.connection = connection;
	}
	
	public ArrayList<ExperienceDTO> getExperience() {
		return experience;
	}

	public void setExperience(ArrayList<ExperienceDTO> experience) {
		this.experience = experience;
	}
	
	public ArrayList<AddressDTO> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<AddressDTO> address) {
		this.address = address;
	}
	
	public ArrayList<SkillDTO> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<SkillDTO> skills) {
		this.skills = skills;
	}
	
	public ArrayList<EducationDTO> getEducations() {
		return educations;
	}

	public void setEducations(ArrayList<EducationDTO> educations) {
		this.educations = educations;
	}
	

}