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
	<#if property.type.typePackage?contains("Primitive")>
	private ${property.type.name} ${property.name?uncap_first};
	</#if>
	</#list>

}