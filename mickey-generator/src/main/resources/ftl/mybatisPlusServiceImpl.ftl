package ${package};

import ${entityPackage};
import ${mapperPackage};
import ${servicePackage};
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

 /**
 * @Description: ${tableName}
 * @author ${author}
 * @date ${time}
 */
@Service("${iocName}")
public class ${implName} extends ServiceImpl<${mapperName},${entityName}> implements ${serviceName} {

}