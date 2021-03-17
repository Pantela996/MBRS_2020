package ${class.typePackage};
import java.util.Set;
import java.util.HashSet;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
<#list imports as import>
import ${import};
</#list>


@Table(name="${class.name?uncap_first}")
@Entity
${class.visibility} class ${class.name} {  

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

<#list properties as property>
	<#if (property.association)??>
	${property.association}
	</#if>
	${property.visibility} ${property.type.name} ${property.name};

</#list>

	public ${class.name}(){}

	
	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}


}
