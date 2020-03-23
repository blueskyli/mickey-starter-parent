package com.mickey.mybatis.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mickey.core.exception.NoveSystemException;
import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.page.QueryResult;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.mapper.Mapper;
import com.mickey.mybatis.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author J·K
 * @Description: AbstractService 此类调用xml的mapper方式
 * @date 2020/3/22 11:35 上午
 */
@Slf4j
public abstract class AbstractService<T extends BasePo,M extends Mapper> implements Service<T> {

    @Autowired
    private M mapper;

    public M getMapper() {
        return this.mapper;
    }

    @Override
    public int insert(T entity, IDataSource... args) {
        filter(args);
        int effectRows = this.mapper.insert(entity);
        return this.RetIdOrEffectRow(entity, effectRows);
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

    private <E> Integer RetIdOrEffectRow(E entity, int effectRows) {
        if(effectRows <= 0){
            throw new NoveSystemException("500","插入数据异常");
        }
        if (entity instanceof BasePo) {
            Class<?> cls = entity.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Id annotation = field.getAnnotation(Id.class);
                if (annotation != null) {
                    try {
                        return Integer.parseInt(field.get(entity).toString());
                    } catch (Exception e) {
                        log.error("获取主键值报错", e);
                        throw new NoveSystemException("500","获取主键值报错");
                    }
                }
            }
        }
        throw new NoveSystemException("500","暂不支持的实体类型");
    }

    private void filter(IDataSource... args){
        if (args.length != 0){
            throw new NoveSystemException("500","this args not support");
        }
    }
}
