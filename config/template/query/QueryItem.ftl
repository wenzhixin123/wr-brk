package ${packageName};

<#if hasDateColumns>
import java.util.Date;

</#if>
import javax.persistence.Column;
import javax.persistence.Entity;

<#if hasLobColumns>
import org.hibernate.annotations.Type;

</#if>
import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class ${className}QueryItem extends BaseQueryItem {

<#list columns as column>
	private ${column.fieldType} ${column.fieldName};
</#list>

<#list columns as column>
	@Column(name = "${column.columnName}")
<#if column.columnType == "BLOB">
	@Type(type="org.springframework.orm.hibernate3.support.BlobByteArrayType")
</#if>
<#if column.columnType == "CLOB">
	@Type(type="org.springframework.orm.hibernate3.support.ClobStringType")
</#if>
	public ${column.fieldType} ${column.getterMethodName}() {
		return ${column.fieldName};
	}

	public void ${column.setterMethodName}(${column.fieldType} ${column.fieldName}) {
		this.${column.fieldName} = ${column.fieldName};
		addValidField("${column.fieldName}");
	}

</#list>
}
