package com.mickey.mybatis.mapper.service.impl.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mickey.core.exception.NoveSystemException;
import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.page.QueryResult;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.mapper.Mapper;
import com.mickey.mybatis.mapper.service.IBaseService;
import com.mickey.model.po.ReflectUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author J·K
 * @Description: AbstractService 此类调用xml的mapper方式
 * @date 2020/3/22 11:35 上午
 */
@Slf4j
public abstract class AbstractService<T extends BasePo,M extends Mapper> implements IBaseService<T> {

    @Autowired
    private M mapper;

    public M getMapper() {
        return this.mapper;
    }

    @SneakyThrows
    protected T getInstance(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class clazz = (Class<T>) type.getActualTypeArguments()[0];
        return  (T) clazz.newInstance();
    }

    @Override
    public int insert(T entity, IDataSource... args) {
        filter(args);
        int effectRows = this.mapper.insert(entity);
        return ReflectUtils.RetId(entity, effectRows);
    }

    @Override
    public int insertList(List<T> list, IDataSource... args) {
        filter(args);
        return this.mapper.insertList(list);
    }

    @Override
    public int delete(T entity, IDataSource... args) {
        filter(args);
        return this.mapper.delete(entity);
    }

    @Override
    public int deleteList(List<T> list, IDataSource... args) {
        filter(args);
        return this.mapper.deleteList(list);
    }

    @Override
    public int update(T entity, IDataSource... args) {
        filter(args);
        return this.mapper.update(entity);
    }

    @Override
    public int updateList(List<T> list, IDataSource... args) {
        filter(args);
        return this.mapper.updateList(list);
    }

    @Override
    public Integer count(T entity, IDataSource... args) {
        filter(args);
        return this.mapper.count(entity);
    }

    @Override
    public T selectOne(T entity, IDataSource... args) {
        filter(args);
        return (T) this.mapper.selectOne(entity);
    }

    @Override
    public List<T> selectList(T entity, IDataSource... args) {
        filter(args);
        return this.mapper.selectList(entity);
    }

    @Override
    public List<T> selectList(List<Integer> list, IDataSource... args) {
        filter(args);
        return this.mapper.selectListByIds(list);
    }

    @Override
    public List<T> selectList(T entity, String orderBy, IDataSource... args) {
        filter(args);
        if(!StringUtils.isEmpty(orderBy))
        {
            PageHelper.orderBy(orderBy);
        }
        return this.mapper.selectList(entity);
    }

    @Override
    public List<T> selectList(T entity, int pageNum, int pageSize, IDataSource... args) {
        filter(args);
        PageHelper.startPage(pageNum, pageSize, false);
        return this.mapper.selectList(entity);
    }

    @Override
    public List<T> selectList(T entity, int pageNum, int pageSize, String orderBy, IDataSource... args) {
        filter(args);
        PageHelper.startPage(pageNum, pageSize, false);
        if(!StringUtils.isEmpty(orderBy))
        {
            PageHelper.orderBy(orderBy);
        }
        return this.mapper.selectList(entity);
    }

    @Override
    public QueryResult<T> selectListAndCount(T entity, Integer pageNum, Integer pageSize, String orderBy, IDataSource... args) {
        filter(args);
        Page<?> page = PageHelper.startPage(pageNum, pageSize);
        if(!StringUtils.isEmpty(orderBy))
        {
            PageHelper.orderBy(orderBy);
        }
        List<T> list = (List<T>) this.mapper.selectList(entity);
        QueryResult<T> result = new QueryResult<>(list, page.getTotal());
        return result;
    }

    private void filter(IDataSource... args){
        if (args.length != 0){
            throw new NoveSystemException("500","this args not support");
        }
    }
}
