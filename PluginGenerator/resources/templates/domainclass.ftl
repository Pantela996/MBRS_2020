package ${class.typePackage};
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import uns.ac.rs.mbrs.dto.${class.name}DTO;
<#list imports as import>
import ${import};
</#list>


@Table(name="${class.name?uncap_first}")
@Entity
${class.visibility} class ${class.name} {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	<#list class.properties as property>

	${property.association}
	<#if property.association?contains("@OneToMany")>
	private ArrayList<${property.type.name}> ${property.name};
	<#else>
	private ${property.type.name?cap_first} ${property.name};
	</#if>
	</#list>

	public ${class.name}(){}
	
	public ${class.name}(${class.name}DTO ${class.name?uncap_first}DTO){
		<#list class.properties as property>
		<#if property.type.typePackage?contains("Primitive")>
		this.${property.name} = ${class.name?uncap_first}DTO.get${property.name?cap_first}();
		</#if>
		</#list>
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	<#list class.properties as property>
	<#if property.association?contains("@OneToMany")>
	public ArrayList<${property.type.name?cap_first}> get${property.name?cap_first}() {
		return ${property.name};
	}

	public void set${property.name?cap_first}(ArrayList<${property.type.name?cap_first}> ${property.name}) {
		this.${property.name} = ${property.name};
	}

	
	<#else>
	public ${property.type.name?cap_first} get${property.name?cap_first}() {
		return ${property.name};
	}

	public void set${property.name?cap_first}(${property.type.name?cap_first} ${property.name}) {
		this.${property.name} = ${property.name};
	}

	
	</#if>
	</#list>

}
