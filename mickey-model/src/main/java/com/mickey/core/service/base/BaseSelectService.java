package com.mickey.core.service.base;

import com.mickey.core.base.annotation.IDataSource;
import com.mickey.core.base.page.QueryResult;
import com.mickey.core.base.po.BasePo;
import com.mickey.core.service.base.select.SelectByStatementService;
import com.mickey.core.service.base.select.SelectService;

import java.util.List;

/**
 * @author J·K
 * @Description: BaseSelectService
 * @date 2020/3/22 10:53 上午
 */
public interface BaseSelectService<T extends BasePo>
        extends SelectService<T>, SelectByStatementService {

























}
