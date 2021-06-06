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
	<#assign primitive=(property.type.typePackage?contains("Primitive") || property.type.name?contains("date"))>
	<#if property.association?contains("@OneToMany")>
	private ArrayList<${property.type.name?cap_first}<#if !primitive>DTO</#if>> ${property.name?uncap_first};
	<#else>
	private ${property.type.name?cap_first}<#if !primitive>DTO</#if> ${property.name?uncap_first};
	</#if>
	</#list>

	<#list class.properties as property>
	<#assign primitive=(property.type.typePackage?contains("Primitive") || property.type.name?contains("date"))>
	<#if property.association?contains("@OneToMany")>
	public ArrayList<${property.type.name}<#if !primitive>DTO</#if>> get${property.name?cap_first}() {
		return ${property.name};
	}

	public void set${property.name?cap_first}(ArrayList<${property.type.name}<#if !primitive>DTO</#if>> ${property.name}) {
		this.${property.name} = ${property.name};
	}
	
	<#else>
	public ${property.type.name?cap_first}<#if !primitive>DTO</#if> get${property.name?cap_first}() {
		return ${property.name};
	}

	public void set${property.name?cap_first}(${property.type.name?cap_first}<#if !primitive>DTO</#if> ${property.name}) {
		this.${property.name} = ${property.name};
	}

	</#if>
	</#list>

}