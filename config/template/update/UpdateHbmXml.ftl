<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC 
	"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
	"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">

<hibernate-mapping>
	<sql-query name="${className}Update">
		<![CDATA[

<#list sqlLines as sqlLine>
${sqlLine.line}
</#list>

		]]>
	</sql-query>
</hibernate-mapping>
