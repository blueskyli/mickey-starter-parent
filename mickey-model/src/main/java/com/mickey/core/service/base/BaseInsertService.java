package com.mickey.core.service.base;

import com.mickey.core.base.po.BasePo;
import com.mickey.core.service.base.insert.InsertByStatementService;
import com.mickey.core.service.base.insert.InsertService;

/**
 * @author J·K
 * @Description: BaseInsertService
 * @date 2020/3/22 10:50 上午
 */
public interface BaseInsertService<T extends BasePo>
        extends InsertService<T>, InsertByStatementService {
}
