package com.mickey.mybatis.po.service.base.insert;

import com.mickey.model.po.BasePo;
import com.mickey.model.functionalInterface.IDataSource;

import java.util.List;

/**
 * @author J·K
 * @Description: InsertService
 * @date 2020/3/22 11:00 上午
 */
public interface InsertService<T extends BasePo> {
    /**
     * 插入数据
     *
     * @param entity
     * @param args
     * @return
     */
    int insert(T entity, IDataSource... args);

    /**
     * 批量插入
     *
     * @param list
     * @param args
     * @return
     */
    int insertList(List<T> list, IDataSource... args);
}
