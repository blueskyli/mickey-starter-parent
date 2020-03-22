package com.mickey.core.service;

import com.mickey.core.base.annotation.IDataSource;
import com.mickey.core.base.po.BasePo;
import com.mickey.core.mapper.base.BaseMapper;

import java.util.List;

/**
 * @author J·K
 * @Description: AbstractService 此类调用xml的mapper方式
 * @date 2020/3/22 11:35 上午
 */
public class AbstractService<T extends BasePo,M extends BaseMapper> implements Service<T>{

    @Override
    public int insert(T entity, IDataSource... args) {
        return 0;
    }

    @Override
    public int insertList(List<T> list, IDataSource... args) {
        return 0;
    }

    @Override
    public int delete(T entity, IDataSource... args) {
        return 0;
    }

    @Override
    public int deleteList(List<T> list, IDataSource... args) {
        return 0;
    }

    @Override
    public int update(T entity, IDataSource... args) {
        return 0;
    }

    @Override
    public int updateList(List<T> list, IDataSource... args) {
        return 0;
    }

    @Override
    public Integer count(T entity, IDataSource... args) {
        return null;
    }

    @Override
    public T selectOne(T entity, IDataSource... args) {
        return null;
    }

    @Override
    public List<T> selectList(T entity, IDataSource... args) {
        return null;
    }

    @Override
    public List<T> selectList(T entity, String orderBy, IDataSource... args) {
        return null;
    }

    @Override
    public List<T> selectList(T entity, int pageNum, int pageSize, IDataSource... args) {
        return null;
    }

    @Override
    public List<T> selectList(T entity, int pageNum, int pageSize, String orderBy, IDataSource... args) {
        return null;
    }
}
