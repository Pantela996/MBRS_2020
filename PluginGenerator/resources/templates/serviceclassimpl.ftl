package ${class.typePackage};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uns.ac.rs.mbrs.model.${class.name};
import uns.ac.rs.mbrs.repository.${class.name}Repository;
import uns.ac.rs.mbrs.service.${class.name}Service;
import uns.ac.rs.mbrs.model.*;

import java.util.Date;


@Service
@Transactional
public class ${class.name}ServiceImpl implements ${class.name}Service {

	@Autowired
	private ${class.name}Repository ${class.name?uncap_first}Repository;
	
	
	@Override
	public ${class.name} findOne(Long id) {
		return ${class.name?uncap_first}Repository.findById(id).get();
	}
	<#if class.uiClass?? && class.uiClass.read == true>

	@Override
	public List<${class.name}> findAll() {
		return ${class.name?uncap_first}Repository.findAll();
	}
	</#if>

	<#if class.uiClass?? && (class.uiClass.create==true || class.uiClass.update == true)>
	@Override
	public ${class.name} save(${class.name} ${class.name?uncap_first}) {
		return ${class.name?uncap_first}Repository.save(${class.name?uncap_first});
	}
	</#if>
	
	<#if class.uiClass?? && class.uiClass.delete == true>
	@Override
	public ${class.name} remove(Long id) {
		${class.name} ${class.name?uncap_first} = ${class.name?uncap_first}Repository.findById(id).get();
		if(${class.name?uncap_first} == null){
			throw new IllegalArgumentException("Tried to delete non-existant ${class.name}");
		}
		${class.name?uncap_first}Repository.delete(${class.name?uncap_first});
		return ${class.name?uncap_first};
	}
	</#if>
	
	<#list properties as property>
		<#if property.name != "id" && property.name != "password" && property.upper == 1 && property.association == false && property.findBy==true>
	@Override
	public List<${class.name}> findBy${property.name?cap_first}(<#if property.type.name == "date"> Date <#else>${property.type.name} </#if> ${property.name}) {
		return ${class.name?uncap_first}Repository.findBy${property.name?cap_first}(${property.name});
	}
	
		</#if>
		<#if property.association == true && property.upper == 1>
	@Override
	public List<${class.name}> findBy<#if property.name != "">${property.name?cap_first}<#else>${property.type.name}</#if>(Long id) {
		return ${class.name?uncap_first}Repository.findBy<#if property.name != "">${property.name?cap_first}<#else>${property.type.name}</#if>(id);
	}
	
		</#if>
 	</#list>
}