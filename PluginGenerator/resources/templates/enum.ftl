package ${enum.typePackage};

public enum ${enum.name}{

	<#list values as e>${e}<#if e_has_next>,</#if></#list>;
}