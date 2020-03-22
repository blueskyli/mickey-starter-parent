package com.mickey.mybatis.dao;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.base.BaseDeleteDao;
import com.mickey.mybatis.dao.base.BaseInsertDao;
import com.mickey.mybatis.dao.base.BaseSelectDao;
import com.mickey.mybatis.dao.base.BaseUpdateDao;

public interface IBaseDao<T extends BasePo>
    extends BaseInsertDao<T>, BaseDeleteDao<T>, BaseUpdateDao<T>, BaseSelectDao<T>
{
}
