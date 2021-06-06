<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Edit ${class.name}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
</head>
<body>
    <p>Hello ${class.name}!</p>
    <form onsubmit="submitForm();">
    <#list class.properties as property>
    <label>${property.name}</label>
    <#if property.type.name?contains("String")>
    <input type="text" id="${property.name}" name="${property.name}" />
    <#elseif property.type.name?contains("Integer")>
    <input type="number" id="${property.name}" name="${property.name}" />
    </#if>
    </#list>
    </form>
</body>
<script>
	$('#editForm').on('submit', function(){
		$.ajax({
			url: "/api/${class.name?uncap_first}",
			type: "POST",
			data: {
			<#list class.properties as property >
				${property.name}: $('#${property.name}').val(),
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