package com.mickey.core.service.base.select;

import com.mickey.core.base.annotation.IDataSource;
import com.mickey.core.base.po.BasePo;

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
