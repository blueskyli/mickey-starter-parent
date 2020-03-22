package com.mickey.mybatis.dao.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.base.update.UpdateByStatementDao;
import com.mickey.mybatis.dao.base.update.UpdateDao;

/**
 * @author J·K
 * @Description: BaseUpdateDao
 * @date 2020/3/22 10:51 上午
 */
public interface BaseUpdateDao<T extends BasePo>
        extends UpdateDao<T>, UpdateByStatementDao {
}
