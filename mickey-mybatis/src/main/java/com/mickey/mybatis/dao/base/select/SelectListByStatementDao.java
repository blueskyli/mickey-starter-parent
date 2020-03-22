package com.mickey.mybatis.dao.base.select;

import java.util.List;

/**
 * @author J·K
 * @Description: SelectListByStatementDao
 * @date 2020/3/22 11:08 上午
 */
public interface SelectListByStatementDao {
    /**
     * 查询一个列表， 不分页,不排序
     *
     * @param statementPostfix 自定义sql名称
     */
    <E> List<E> selectList(String statementPostfix, E entity);

    /**
     * 查询一个列表， 不分页
     *
     * @param statementPostfix 自定义sql名称
     * @param orderBy          排序sql 例：'id desc'
     */
    <E> List<E> selectList(String statementPostfix, E entity, String orderBy);

    /**
     * 分页查询 不排序
     *
     * @param statementPostfix
     * @param entity
     * @param pageNum          页码
     * @param pageSize         每页记录数
     * @return
     */
    <E> List<E> selectList(String statementPostfix, E entity, Integer pageNum, Integer pageSize);

    /**
     * 分页查询
     *
     * @param statementPostfix
     * @param entity
     * @param pageNum          页码
     * @param pageSize         每页记录数
     * @param orderBy          排序sql 例：'id desc'
     * @return
     */
    <E> List<E> selectList(String statementPostfix, E entity, Integer pageNum, Integer pageSize, String orderBy);
}
