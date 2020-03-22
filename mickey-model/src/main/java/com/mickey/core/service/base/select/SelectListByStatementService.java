package com.mickey.core.service.base.select;

import com.mickey.core.base.annotation.IDataSource;

import java.util.List;

/**
 * @author J·K
 * @Description: SelectListByStatementService
 * @date 2020/3/22 11:08 上午
 */
public interface SelectListByStatementService {
    /**
     * 根据条件查询列表
     *
     * @param statementPostfix
     * @param entity
     * @param args
     * @param <E>
     * @return
     */
    <E> List<E> selectList(String statementPostfix, E entity, IDataSource... args);
    /**
     * 根据条件查询列表
     *
     * @param statementPostfix
     * @param entity
     * @param orderBy
     * @param args
     * @param <E>
     * @return
     */
    <E> List<E> selectList(String statementPostfix, E entity, String orderBy, IDataSource... args);
    /**
     * 根据条件查询列表并分页
     *
     * @param statementPostfix
     * @param entity
     * @param pageNum
     * @param pageSize
     * @param args
     * @param <E>
     * @return
     */
    <E> List<E> selectList(String statementPostfix, E entity, int pageNum, int pageSize, IDataSource... args);
    /**
     * 根据条件查询列表并分页
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
    <E> List<E> selectList(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, IDataSource... args);
}
