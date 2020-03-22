package com.mickey.core.service.base.update;

import com.mickey.core.base.po.BasePo;
import com.mickey.core.base.annotation.IDataSource;

import java.util.List;

/**
 * @author J·K
 * @Description: UpdateService
 * @date 2020/3/22 11:03 上午
 */
public interface UpdateService<T extends BasePo> {
    /**
     * 更新数据
     *
     * @param entity
     * @param args
     * @return
     */
    int update(T entity, IDataSource... args);
    /**
     * 批量更新
     *
     * @param list
     * @param args
     * @return
     */
    int updateList(List<T> list, IDataSource... args);
}
