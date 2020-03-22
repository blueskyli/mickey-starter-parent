package com.mickey.mybatis.dao.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.base.delete.DeleteByStatementServiceDao;
import com.mickey.mybatis.dao.base.delete.DeleteDao;

/**
 * @author J·K
 * @Description: BaseDeleteService
 * @date 2020/3/22 10:47 上午
 */
public interface BaseDeleteDao<T extends BasePo>
        extends DeleteDao<T>, DeleteByStatementServiceDao {
}
