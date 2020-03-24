package com.mickey.mybatis.po.service.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.po.service.base.update.UpdateByStatementService;
import com.mickey.mybatis.po.service.base.update.UpdateService;

/**
 * @author J·K
 * @Description: BaseUpdateService
 * @date 2020/3/22 10:51 上午
 */
public interface BaseUpdateService<T extends BasePo>
        extends UpdateService<T>, UpdateByStatementService {
}
