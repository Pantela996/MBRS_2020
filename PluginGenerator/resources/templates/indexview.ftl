<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Index ${class.name}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>
</head>
<body>
    <p>Index ${class.name}!</p>
    <div id="content">
    <table>
        <tbody>
		    <tr th:each="listElement: ${r"${"}list}">
				<#list class.properties as property>
				<#if property.hidden != true>
				    <#if property.type == "String">
				    	<label>${property.name}</label>
			       		<td th:text="${r"${"}listElement.${property.name}}" />
			        <#elseif property.type == "Integer">
			        	<label>${property.name}</label>
			       		<td th:number="${r"${"}listElement.${property.name}}" />
			       	<#elseif property.type == "date">
			       		<label>${property.name}</label>
			       		<td th:date="${r"${"}listElement.${property.name}}" />
			        </#if>
		        </#if>
		        </#list>
		    </tr>
	    </tbody>
	</table>
    </div>
</body>

</html> 