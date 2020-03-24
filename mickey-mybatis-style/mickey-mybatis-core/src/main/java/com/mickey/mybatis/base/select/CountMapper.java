package com.mickey.mybatis.base.select;

import com.mickey.model.po.BasePo;

/**
 * @author J·K
 * @Description: CountMapper
 * @date 2020/3/22 11:06 上午
 */
public interface CountMapper<T extends BasePo> {
    /**
     * 查询统计 根据对象条件统计
     *
     * @param entity
     * @return
     */
    Integer count(T entity);
}
