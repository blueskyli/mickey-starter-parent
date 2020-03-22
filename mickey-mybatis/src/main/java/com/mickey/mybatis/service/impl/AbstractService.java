package com.mickey.mybatis.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.page.QueryResult;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.mapper.BaseMapper;
import com.mickey.mybatis.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author J·K
 * @Description: AbstractService 此类调用xml的mapper方式
 * @date 2020/3/22 11:35 上午
 */
public class AbstractService<T extends BasePo,M extends BaseMapper> implements Service<T> {

    @Autowired
    private M mapper;

    public M getMapper() {
        return this.mapper;
    }

    @Override
    public int insert(T entity, IDataSource... args) {
        return this.mapper.insert(entity);
    }

    @Override
    public int insertList(List<T> list, IDataSource... args) {
        return this.mapper.insert(list);
    }

    @Override
    public int delete(T entity, IDataSource... args) {
        return this.mapper.delete(entity);
    }

    @Override
    public int deleteList(List<T> list, IDataSource... args) {
        return this.mapper.delete(list);
    }

    @Override
    public int update(T entity, IDataSource... args) {
        return this.mapper.update(entity);
    }

    @Override
    public int updateList(List<T> list, IDataSource... args) {
        return this.mapper.update(list);
    }

    @Override
    public Integer count(T entity, IDataSource... args) {
        return this.mapper.count(entity);
    }

    @Override
    public T selectOne(T entity, IDataSource... args) {
        return (T) this.mapper.selectOne(entity);
    }

    @Override
    public List<T> selectList(T entity, IDataSource... args) {
        return this.mapper.selectList(entity);
    }

    @Override
    public List<T> selectList(T entity, String orderBy, IDataSource... args) {
        if(!StringUtils.isEmpty(orderBy))
        {
            PageHelper.orderBy(orderBy);
        }
        return this.mapper.selectList(entity);
    }

    @Override
    public List<T> selectList(T entity, int pageNum, int pageSize, IDataSource... args) {
        PageHelper.startPage(pageNum, pageSize, false);
        return this.mapper.selectList(entity);
    }

    @Override
    public List<T> selectList(T entity, int pageNum, int pageSize, String orderBy, IDataSource... args) {
        PageHelper.startPage(pageNum, pageSize, false);
        if(!StringUtils.isEmpty(orderBy))
        {
            PageHelper.orderBy(orderBy);
        }
        return this.mapper.selectList(entity);
    }

    @Override
    public QueryResult<T> selectListAndCount(T entity, Integer pageNum, Integer pageSize, String orderBy) {
        Page<?> page = PageHelper.startPage(pageNum, pageSize);
        if(!StringUtils.isEmpty(orderBy))
        {
            PageHelper.orderBy(orderBy);
        }
        List<T> list = (List<T>) this.mapper.selectList(entity);
        QueryResult<T> result = new QueryResult<>(list, page.getTotal());
        return result;
    }
}
