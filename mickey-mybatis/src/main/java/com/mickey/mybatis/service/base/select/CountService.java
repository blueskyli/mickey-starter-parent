package com.mickey.mybatis.service.base.select;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.po.BasePo;

/**
 * @author J·K
 * @Description: CountService
 * @date 2020/3/22 11:06 上午
 */
public interface CountService<T extends BasePo> {
    /**
     * 查询符合条件的总条数
     * @param entity
     * @param args
     * @return
     */
    Integer count(T entity, IDataSource... args);
}
