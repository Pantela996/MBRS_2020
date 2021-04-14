package ${class.typePackage};

import import uns.ac.rs.mbrs.model.${class.name};

import java.util.List;
import java.util.Date;
import uns.ac.rs.mbrs.model.*;

public interface ${class.name}Service{

	
	${class.name} findOne(Long id); 
	
	<#if class.uiClass?? && ( class.uiClass.create == true || class.uiClass.update)>
	${class.name} save(${class.name} ${class.name?uncap_first});
	</#if>
	
	<#if class.uiClass?? && class.uiClass.read == true>
	List<${class.name}> findAll();
	</#if>
	<#if class.uiClass?? && class.uiClass.delete == true>
	${class.name} remove(Long id);
	</#if>
	

	<#list properties as property>
		<#if property.name != "id" && property.name != "password" && property.upper == 1 && property.association == false && property.findBy==true>
	List<${class.name}> findBy${property.name?cap_first}(<#if property.type.name == "date"> Date <#else>${property.type.name} </#if> ${property.name});

		</#if>
		<#if property.association == true && property.upper == 1>
	List<${class.name}> findBy<#if property.name != "">${property.name?cap_first}<#else>${property.type.name}</#if>(Long id);

		</#if>
 	</#list>
}