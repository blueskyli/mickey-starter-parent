package com.mickey.mybatis.service.base.select;

import com.mickey.model.functionalInterface.IDataSource;

/**
 * @author J·K
 * @Description: CountByStatementService
 * @date 2020/3/22 11:06 上午
 */
public interface CountByStatementService {
    /**
     * 查询符合条件的总条数
     *
     * @param statementPostfix
     * @param entity
     * @param args
     * @param <E>
     * @return
     */
    <E> Integer count(String statementPostfix, E entity, IDataSource... args);
}
