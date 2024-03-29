package com.mickey.mybatis.base.insert;

import com.mickey.model.po.BasePo;

import java.util.List;

/**
 * @author J·K
 * @Description: InsertMapper
 * @date 2020/3/22 4:25 下午
 */
public interface InsertMapper<T extends BasePo> {
    /**
     * 插入一条记录
     *
     * @param entity
     * @return @
     */
    Integer insert(T entity);
    /**
     * 批量插入数据
     *
     * @param list
     * @return @
     */
    Integer insertList(List<T> list);
}
