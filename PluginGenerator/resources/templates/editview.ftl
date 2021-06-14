<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Edit ${class.name}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
</head>
<body>
    <p>Hello ${class.name}!</p>
    <form id="editForm">
    <#list class.properties as property>
	    <#if property.type == "String" >  
		    <label>${property.name}</label>
		    <input type="text" id="${property.name}" name="${property.name}" />
	    <#elseif property.type == "Integer" >  
		    <label>${property.name}</label>
		    <input type="number" id="${property.name}" name="${property.name}" />
		<#elseif property.type == "date" >  
            <label>${property.name}</label>
            <input type="date" id="${property.name}" name="${property.name}" />
        <#elseif property.type == "password" >  
            <label>${property.name}</label>
            <input type="password" id="${property.name}" name="${property.name}" />
	    </#if>
    </#list>
    </form>
</body>
<script>
	$('#editForm').on('submit', function(){
		$.ajax({
			url: "/${class.name?uncap_first}",
			type: "PUT",
			data: {
			<#list class.properties as property >
			<#if property.type.name?contains("String") || property.type.name?contains("Integer")>
				${property.name}: $('#${property.name}').val(),
			</#if>
			</#list>
			},
			dataType: 'json',
			success: function(res){
				console.log(res);

			}
		});
	});
</script>
</html>