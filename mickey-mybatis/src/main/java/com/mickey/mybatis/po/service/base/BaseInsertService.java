package com.mickey.mybatis.po.service.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.po.service.base.insert.InsertByStatementService;
import com.mickey.mybatis.po.service.base.insert.InsertService;

/**
 * @author J·K
 * @Description: BaseInsertService
 * @date 2020/3/22 10:50 上午
 */
public interface BaseInsertService<T extends BasePo>
        extends InsertService<T>, InsertByStatementService {
}
