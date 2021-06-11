<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Edit ${class.name}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
</head>
<body>
    <p>Hello ${class.name}!</p>
    <form id="newForm">
    <#list class.properties as property>
    <#if property.type.name?contains("String")>
    <label>${property.name}</label>
    <input type="text" id="${property.name}" name="${property.name}" />
    <#elseif property.type.name?contains("Integer")>
    <label>${property.name}</label>
    <input type="number" id="${property.name}" name="${property.name}" />
    </#if>
    </#list>
    </form>
</body>
<script>
	$('#newForm').on('submit', function(){
		$.ajax({
			url: "/${class.name?uncap_first}",
			type: "POST",
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