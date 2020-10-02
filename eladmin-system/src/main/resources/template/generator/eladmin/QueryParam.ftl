package ${package}.service.dto;

import lombok.Getter;
import lombok.Setter;
<#if queryHasTimestamp>
import java.sql.Timestamp;
</#if>
<#if queryHasBigDecimal>
import java.math.BigDecimal;
</#if>
<#if betweens??>
import java.util.List;
</#if>
import java.util.Date;
<#if queryColumns??>
import me.zhengjie.annotation.Query;
</#if>
import org.springframework.format.annotation.DateTimeFormat;

/**
* @author ${author}
* @date ${date}
*/
@Getter
@Setter
public class ${className}QueryParam{
<#if queryColumns??>
    <#list queryColumns as column>

<#if column.queryType = '='>
    /** 精确 */
    @Query
    private ${column.columnType} ${column.changeColumnName};
</#if>
<#if column.queryType = 'Like'>
    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private ${column.columnType} ${column.changeColumnName};
</#if>
<#if column.queryType = '!='>
    /** 不等于 */
    @Query(type = Query.Type.NOT_EQUAL)
    private ${column.columnType} ${column.changeColumnName};
</#if>
<#if column.queryType = 'NotNull'>
    /** 不为空 */
    @Query(type = Query.Type.NOT_NULL)
    private ${column.columnType} ${column.changeColumnName};
</#if>
<#if column.queryType = '>='>
    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    private ${column.columnType} ${column.changeColumnName};
</#if>
<#if column.queryType = '<='>
    /** 小于等于 */
    @Query(type = Query.Type.LESS_THAN)
    private ${column.columnType} ${column.changeColumnName};
</#if>
    </#list>
</#if>

<#if betweens??>
    <#list betweens as column>
    /** BETWEEN */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Query(type = Query.Type.BETWEEN)
    private List<${column.columnType}> ${column.changeColumnName};
    </#list>
</#if>
}
