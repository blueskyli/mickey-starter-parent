package com.mickey.mybatis.dao.base.select;

import com.mickey.model.po.BasePo;

import java.util.List;

/**
 * @author J·K
 * @Description: SelectListDao
 * @date 2020/3/22 11:07 上午
 */
public interface SelectListDao<T extends BasePo> {
    /**
     * 查询一个列表， 不分页,不排序
     *
     * @param entity 实体对象
     * @return
     */
    List<T> selectList(T entity);
    /**
     * 根据主键数组查询一个列表， 不分页,不排序
     *
     * @param entity  实体对象
     * @param list 主键数组
     * @return
     */
    List<T> selectList(T entity,List<Integer> list);
    /**
     * 查询一个列表， 不分页
     *
     * @param entity  实体对象
     * @param orderBy 排序sql 例：'id desc'
     * @return
     */
    List<T> selectList(T entity, String orderBy);
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
     * 分页查询
     *
     * @param entity
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @param orderBy  排序sql 例：'id desc'
     * @return
     */
    List<T> selectList(T entity, Integer pageNum, Integer pageSize, String orderBy);
}
