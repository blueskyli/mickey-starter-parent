package com.mickey.mybatis.mapper.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.base.update.UpdateDao;

/**
 * @author J·K
 * @Description: BaseUpdateMapper
 * @date 2020/3/22 9:56 上午
 */
public interface BaseUpdateMapper<T extends BasePo> extends UpdateDao<T> {
}
