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
			    <#if property.type.typePackage?contains("Primitive")>
		       	<td th:text="${r"${"}listElement.${property.name}}" />
		        </#if>
		        </#list>
		    </tr>
	    </tbody>
	</table>
    </div>
</body>

</html> 