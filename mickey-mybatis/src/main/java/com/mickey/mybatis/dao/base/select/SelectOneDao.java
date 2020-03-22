package com.mickey.mybatis.dao.base.select;

import com.mickey.model.po.BasePo;

/**
 * @author J·K
 * @Description: SelectOneDao
 * @date 2020/3/22 11:07 上午
 */
public interface SelectOneDao<T extends BasePo> {
    /**
     * 根据 T 查询数据
     *
     * @param entity
     * @return
     */
    T selectOne(T entity);
}
