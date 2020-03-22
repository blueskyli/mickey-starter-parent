package com.mickey.core.dao.base;

import com.mickey.core.base.page.QueryResult;
import com.mickey.core.base.po.BasePo;

import java.util.List;

public interface IBaseDao<T extends BasePo>
{
    /**
     * 查询统计 根据对象条件统计
     *
     * @param entity
     * @return
     */
    Integer count(T entity);

    /**
     * 查询统计
     *
     * @param statementPostfix 自定义方法
     * @param entity           PO对象
     * @return
     */
    <E> Integer count(String statementPostfix, E entity);

    /**
     * 根据 T 查询数据
     *
     * @param entity
     * @return
     */
    T selectOne(T entity);

    /**
     * 自定义查询数据
     *
     * @param statementPostfix 自定义方法名称
     * @param entity           数据对象
     * @return
     */
    <E> E selectOne(String statementPostfix, E entity);

    /**
     * 插入一条记录
     *
     * @param entity
     * @return @
     */
    Integer insert(T entity);

    /**
     * 插入对象
     *
     * @param statementPostfix
     * @param entity
     * @return @
     */
    <E> Integer insert(String statementPostfix, E entity);

    /**
     * 批量插入数据
     *
     * @param list
     * @return @
     */
    Integer insert(List<T> list);

    /**
     * 更新对象(此方法只根据主键ID进行更新)
     *
     * @param entity
     * @return @
     */
    Integer update(T entity);

    /**
     * 自定义更新方法
     *
     * @param statementPostfix 方法路径名称
     * @param entity
     * @return @
     */
    <E> Integer update(String statementPostfix, E entity);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    Integer update(List<T> list);

    /**
     * 删除
     *
     * @param entity
     * @return @
     */
    Integer delete(T entity);

    /**
     * 自定义删除方法
     *
     * @param statementPostfix
     * @param entity
     * @return @
     */
    <E> Integer delete(String statementPostfix, E entity);

    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    Integer delete(List<T> list);

    /**
     * 查询一个列表， 不分页,不排序
     *
     * @param entity 实体对象
     * @return
     */
    List<T> selectList(T entity);

    /**
     * 查询一个列表， 不分页,不排序
     *
     * @param statementPostfix 自定义sql名称
     */
    <E> List<E> selectList(String statementPostfix, E entity);

    /**
     * 查询一个列表， 不分页
     *
     * @param entity  实体对象
     * @param orderBy 排序sql 例：'id desc'
     * @return
     */
    List<T> selectList(T entity, String orderBy);

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
     * @param entity
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return
     */
    List<T> selectList(T entity, Integer pageNum, Integer pageSize);

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
     * @param entity
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @param orderBy  排序sql 例：'id desc'
     * @return
     */
    List<T> selectList(T entity, Integer pageNum, Integer pageSize, String orderBy);

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
