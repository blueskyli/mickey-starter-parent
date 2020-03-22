package com.mickey.mybatis.service.base.update;

import com.mickey.model.functionalInterface.IDataSource;

import java.util.List;

/**
 * @author J·K
 * @Description: UpdateByStatementService
 * @date 2020/3/22 11:03 上午
 */
public interface UpdateByStatementService {
    /**
     * 更新数据
     *
     * @param statementPostfix
     * @param entity
     * @param args
     * @param <E>
     * @return
     */
    <E> int update(String statementPostfix, E entity, IDataSource... args);
    /**
     * 批量更新
     *
     * @param statementPostfix
     * @param list
     * @param args
     * @param <E>
     * @return
     */
    <E> int updateList(String statementPostfix, List<E> list, IDataSource... args);
}
