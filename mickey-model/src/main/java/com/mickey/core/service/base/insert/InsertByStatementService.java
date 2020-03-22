package com.mickey.core.service.base.insert;

import com.mickey.core.base.annotation.IDataSource;

import java.util.List;

/**
 * @author J·K
 * @Description: InsertByStatementService
 * @date 2020/3/22 11:00 上午
 */
public interface InsertByStatementService {
    /**
     * 插入数据
     *
     * @param statementPostfix
     * @param entity
     * @param args
     * @return
     */
    <E> int insert(String statementPostfix, E entity, IDataSource... args);

    /**
     * 批量插入
     *
     * @param statementPostfix
     * @param list
     * @param args
     * @param <E>
     * @return
     */
    <E> int insertList(String statementPostfix, List<E> list, IDataSource... args);
}
