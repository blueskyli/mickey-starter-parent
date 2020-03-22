package com.mickey.mybatis.service.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.service.base.delete.DeleteByStatementService;
import com.mickey.mybatis.service.base.delete.DeleteService;

/**
 * @author J·K
 * @Description: BaseDeleteService
 * @date 2020/3/22 10:47 上午
 */
public interface BaseDeleteService<T extends BasePo>
        extends DeleteService<T>, DeleteByStatementService {
}
