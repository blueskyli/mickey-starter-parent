package com.mickey.mybatis.service.base.select;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.po.BasePo;

import java.util.List;

/**
 * @author J·K
 * @Description: SelectListService
 * @date 2020/3/22 11:07 上午
 */
public interface SelectListService<T extends BasePo> {
    /**
     * 根据条件查询列表
     *
     * @param entity
     * @param args
     * @return
     */
    List<T> selectList(T entity, IDataSource... args);
    /**
     * 根据条件查询列表
     *
     * @param entity
     * @param orderBy
     * @param args
     * @return
     */
    List<T> selectList(T entity, String orderBy, IDataSource... args);
    /**
     * 根据条件查询列表并分页
     *
     * @param entity
     * @param pageNum
     * @param pageSize
     * @param args
     * @return
     */
    List<T> selectList(T entity, int pageNum, int pageSize, IDataSource... args);
    /**
     * 根据条件查询列表并分页
     *
     * @param entity
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @param args
     * @return
     */
    List<T> selectList(T entity, int pageNum, int pageSize, String orderBy, IDataSource... args);
}