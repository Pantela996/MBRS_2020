package myplugin.generator.fmmodel;

public class FMAssociation extends FMType {

	private String firstMemberClass;

	private String secondMemberClass;

	private Integer firstMemberLower;
	private Integer firstMemberUpper;

	private Integer secondMemberLower;
	private Integer secondMemberUpper;

	public FMAssociation(String name, String typePackage) {
		super(name, typePackage);
		// TODO Auto-generated constructor stub
	}

	public String getFirstMemberClass() {
		return firstMemberClass;
	}

	public void setFirstMemberClass(String firstMemberClass) {
		this.firstMemberClass = firstMemberClass;
	}

	public String getSecondMemberClass() {
		return secondMemberClass;
	}

	public void setSecondMemberClass(String secondMemberClass) {
		this.secondMemberClass = secondMemberClass;
	}

	public Integer getFirstMemberLower() {
		return firstMemberLower;
	}

	public void setFirstMemberLower(Integer firstMemberLower) {
		this.firstMemberLower = firstMemberLower;
	}

	public Integer getFirstMemberUpper() {
		return firstMemberUpper;
	}

	public void setFirstMemberUpper(Integer firstMemberUpper) {
		this.firstMemberUpper = firstMemberUpper;
	}

	public Integer getSecondMemberLower() {
		return secondMemberLower;
	}

	public void setSecondMemberLower(Integer secondMemberLower) {
		this.secondMemberLower = secondMemberLower;
	}

	public Integer getSecondMemberUpper() {
		return secondMemberUpper;
	}

	public void setSecondMemberUpper(Integer secondMemberUpper) {
		this.secondMemberUpper = secondMemberUpper;
	}
	
	public String determineType() {
		if (firstMemberLower == 1 && firstMemberUpper == 1 
				&& secondMemberLower == 1 && secondMemberUpper == 1) {
			return "OneToOne";
		} else if (firstMemberUpper == -1 && secondMemberUpper == -1) {
			return "ManyToMany";
		} else if (firstMemberUpper == -1 && secondMemberUpper == 1) {
			return "ManyToOne";
		} else if  (firstMemberUpper == 1 && secondMemberUpper == -1) {
			return "OneToMany";
		}
		return "Column";
			
	}

}
