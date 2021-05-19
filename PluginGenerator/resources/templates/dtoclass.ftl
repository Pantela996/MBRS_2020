package ${class.typePackage};

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList; 
<#list imports as i>
import ${i};
</#list>


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
${class.visibility} class ${class.name}DTO{
	private Long id;
	
	<#list class.properties as property>
	${property.association}
	private ${property.type.name} ${property.name?uncap_first}
	</#list>s
}