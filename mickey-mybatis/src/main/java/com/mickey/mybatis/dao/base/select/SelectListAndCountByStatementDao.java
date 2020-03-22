package com.mickey.mybatis.dao.base.select;

import com.mickey.model.page.QueryResult;

/**
 * @author J·K
 * @Description: SelectListAndCountByStatementDao
 * @date 2020/3/22 11:08 上午
 */
public interface SelectListAndCountByStatementDao {
    /**
     * 此方法返回数据结果集和count
     *
     * @param statementPostfix 自定义方法名(mybatis namespace)
     * @param entity           参数对象
     * @param pageNum          页码
     * @param pageSize         每页记录数
     * @param orderBy          排序字段(例：'id desc')
     * @return
     */
    <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, Integer pageNum, Integer pageSize, String orderBy);

    /**
     * 此方法返回数据结果集和count
     *
     * @param statementPostfix 自定义方法名(mybatis namespace)
     * @param entity           参数对象
     * @param pageNum          页码
     * @param pageSize         每页记录数
     * @param orderBy          排序字段(例：'id desc')
     * @param statementCount   自定义count查询语句
     * @return
     */
    <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, Integer pageNum, Integer pageSize, String orderBy, String statementCount);
}
