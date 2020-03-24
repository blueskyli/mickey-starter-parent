package com.mickey.mybatis.po.service.base.select;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.po.BasePo;

/**
 * @author J·K
 * @Description: SelectOneService
 * @date 2020/3/22 11:07 上午
 */
public interface SelectOneService<T extends BasePo> {
    /**
     * 根据查询条件查询一条数据
     *
     * @param entity
     * @param args
     * @return
     */
    T selectOne(T entity, IDataSource... args);
}
