package com.mickey.mybatis.po.service.base.delete;

import com.mickey.model.po.BasePo;
import com.mickey.model.functionalInterface.IDataSource;

import java.util.List;

/**
 * @author J·K
 * @Description: DeleteService
 * @date 2020/3/22 10:56 上午
 */
public interface DeleteService<T extends BasePo>{
    /**
     * 删除
     *
     * @param entity
     * @param args
     * @return
     */
    @Deprecated
    int delete(T entity, IDataSource... args);

    /**
     * 批量删除
     *
     * @param list
     * @param args
     * @return
     */
    @Deprecated
    int deleteList(List<T> list, IDataSource... args);
}
