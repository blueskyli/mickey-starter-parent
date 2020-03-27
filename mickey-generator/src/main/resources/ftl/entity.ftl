package ${package};

import com.alibaba.fastjson.JSON;
import com.mickey.model.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "${tableName}")
public class ${className} extends BasePo {

    private static final long serialVersionUID = 1L;

    public static final String TABLE_ALIAS = "${tableAlias}";
    
    ${properties}

	@Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
}