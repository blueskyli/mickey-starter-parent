package com.mickey.mybatis.mapper.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.base.insert.InsertDao;

/**
 * @author J·K
 * @Description: BaseInsertMapper
 * @date 2020/3/22 9:54 上午
 */
public interface BaseInsertMapper<T extends BasePo> extends InsertDao<T> {
}
