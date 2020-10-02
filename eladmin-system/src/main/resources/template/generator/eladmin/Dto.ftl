package ${package}.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;
<#if hasTimestamp>
import java.sql.Timestamp;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
<#if !auto && pkColumnType == 'Long'>
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
</#if>

/**
* @author ${author}
* @date ${date}
*/
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ${className}Dto implements Serializable {
    private static final long serialVersionUID = 1L;
<#if columns??>
    <#list columns as column>

    <#if column.columnKey = 'PRI'>
    <#if !auto && pkColumnType = 'Long'>
    /** 防止精度丢失 */
    @JsonSerialize(using= ToStringSerializer.class)
    </#if>
    </#if>
    <#if column.changeColumnName != 'delFlag'>
    private ${column.columnType} ${column.changeColumnName};
    </#if>
    </#list>
</#if>
}
