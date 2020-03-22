package com.mickey.mybatis.service.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.service.base.select.SelectByStatementService;
import com.mickey.mybatis.service.base.select.SelectService;

/**
 * @author J·K
 * @Description: BaseSelectService
 * @date 2020/3/22 10:53 上午
 */
public interface BaseSelectService<T extends BasePo>
        extends SelectService<T>, SelectByStatementService {

























}
