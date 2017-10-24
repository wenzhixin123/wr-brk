package ${packageName}.model;

<#if hasDateColumns>
import java.util.Date;

</#if>
import javax.persistence.Column;
import javax.persistence.Entity;
<#if (pkFieldType == "String" && pkFieldLength?number >= 36) || pkFieldType == "Integer" || pkFieldType == "Long">
import javax.persistence.GeneratedValue;
</#if>
<#if pkFieldType == "Integer" || pkFieldType == "Long">
import javax.persistence.GenerationType;
</#if>
import javax.persistence.Id;
<#if pkFieldType == "Integer" || pkFieldType == "Long">
import javax.persistence.SequenceGenerator;
</#if>
import javax.persistence.Table;
<#if hasVersionColumn>
import javax.persistence.Version;
</#if>

<#if hasLobColumns>
import org.hibernate.annotations.Type;

</#if>
import com.sinotrans.framework.core.model.BaseModelClass;
<#if implementsOperationLog>
import com.sinotrans.framework.core.model.OperationLog;
</#if>

/**
 * Model class for ${label}
<#list remarkLines as remarkLine>
 * ${remarkLine}
</#list>
 */
@Entity
@Table(name = "${tableName}")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class ${className}Model extends BaseModelClass <#if implementsOperationLog>implements OperationLog </#if>{

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "${className}";

	public static final class FieldNames {
<#list columns as column>
		/**
		 * ${column.label}
		 */
		public static final String ${column.fieldName} = "${column.fieldName}";
</#list>
	}

<#list columns as column>
	//${column.label}
	private ${column.fieldType} ${column.fieldName};
</#list>

<#list columns as column>
	/**
	 * Get ${column.label}
<#list column.remarkLines as remarkLine>
	 * ${remarkLine}
</#list>
	 */
	@Column(name = "${column.columnName}")
<#if column.isPK>
<#if pkFieldType == "String" && (pkFieldLength?number >= 36)>
	@Id @GeneratedValue(generator = "UUIDGenerator")
<#elseif pkFieldType == "Integer" || pkFieldType == "Long">
	@SequenceGenerator(name="SEQ_${tableName}", sequenceName="SEQ_${tableName}")
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_${tableName}")
<#else>
	@Id
</#if>
</#if>
<#if column.columnType == "BLOB">
	@Type(type="org.springframework.orm.hibernate3.support.BlobByteArrayType")
</#if>
<#if column.columnType == "CLOB">
	@Type(type="org.springframework.orm.hibernate3.support.ClobStringType")
</#if>
<#if column.isVersion>
	@Version
</#if>
	public ${column.fieldType} ${column.getterMethodName}() {
		return ${column.fieldName};
	}

	/**
	 * Set ${column.label}
<#list column.remarkLines as remarkLine>
	 * ${remarkLine}
</#list>
	 */
	public void ${column.setterMethodName}(${column.fieldType} ${column.fieldName}) {
		this.${column.fieldName} = ${column.fieldName};
		addValidField(FieldNames.${column.fieldName});
	}

</#list>
}
