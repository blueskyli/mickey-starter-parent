package com.mickey.mybatis.dao.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.base.select.SelectByStatementDao;
import com.mickey.mybatis.dao.base.select.SelectDao;

/**
 * @author J·K
 * @Description: BaseSelectDao
 * @date 2020/3/22 10:53 上午
 */
public interface BaseSelectDao<T extends BasePo>
        extends SelectDao<T>, SelectByStatementDao {

























}
