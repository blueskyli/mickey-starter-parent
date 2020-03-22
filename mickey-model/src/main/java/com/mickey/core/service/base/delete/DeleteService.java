package com.mickey.core.service.base.delete;

import com.mickey.core.base.po.BasePo;
import com.mickey.core.base.annotation.IDataSource;

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
