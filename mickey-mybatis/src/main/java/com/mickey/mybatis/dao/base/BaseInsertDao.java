package com.mickey.mybatis.dao.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.base.insert.InsertByStatementDao;
import com.mickey.mybatis.dao.base.insert.InsertDao;

/**
 * @author J·K
 * @Description: BaseInsertService
 * @date 2020/3/22 10:50 上午
 */
public interface BaseInsertDao<T extends BasePo>
        extends InsertDao<T>, InsertByStatementDao {
}
