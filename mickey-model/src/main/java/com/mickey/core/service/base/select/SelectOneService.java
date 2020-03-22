package com.mickey.core.service.base.select;

import com.mickey.core.base.annotation.IDataSource;
import com.mickey.core.base.po.BasePo;

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
