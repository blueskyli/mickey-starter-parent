package com.mickey.core.service.base;

import com.mickey.core.base.po.BasePo;
import com.mickey.core.service.base.update.UpdateByStatementService;
import com.mickey.core.service.base.update.UpdateService;

/**
 * @author J·K
 * @Description: BaseUpdateService
 * @date 2020/3/22 10:51 上午
 */
public interface BaseUpdateService<T extends BasePo>
        extends UpdateService<T>, UpdateByStatementService {
}
