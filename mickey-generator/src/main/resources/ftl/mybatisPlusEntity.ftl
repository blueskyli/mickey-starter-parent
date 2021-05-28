package ${package};

import com.alibaba.fastjson.JSON;
import com.mickey.model.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
${import}

 /**
 * 
 * @Description: ${tableName}
 * @author ${author}
 * @date ${time}
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@TableName(value = "${tableName}")
public class ${className} extends BasePo {

    public static final String TABLE_ALIAS = "${tableAlias}";
    
<#--    ${properties}-->
<#list fields as field>
    /**
    * ${field.fieldRemark}
    */
<#if field.primaryKey?string('yes','no') == 'yes'>
    @TableId(value = "${field.columnName}", type = IdType.AUTO)
<#else>
    @TableField(value = "${field.columnName}")
</#if>
    private ${field.fieldType} ${field.fieldName};
</#list>

	@Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
}