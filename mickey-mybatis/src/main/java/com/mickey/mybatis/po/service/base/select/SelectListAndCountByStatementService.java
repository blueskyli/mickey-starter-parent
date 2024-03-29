package com.mickey.mybatis.po.service.base.select;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.page.QueryResult;

/**
 * @author J·K
 * @Description: SelectListAndCountByStatementService
 * @date 2020/3/22 11:08 上午
 */
public interface SelectListAndCountByStatementService {
    /**
     * 分页查询数据并返回总条数
     *
     * @param statementPostfix
     * @param entity
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param args
     * @param <E>
     * @return
     */
    <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, IDataSource... args);

    /**
     * 分页查询数据并返回总条数
     *
     * @param statementPostfix
     * @param entity
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param statementCount
     * @param args
     * @param <E>
     * @return
     */
    <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, String statementCount, IDataSource... args);
}
