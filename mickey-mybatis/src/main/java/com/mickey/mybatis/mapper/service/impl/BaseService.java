package com.mickey.mybatis.mapper.service.impl;

import com.google.common.collect.Lists;
import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.po.BasePo;
import com.mickey.model.po.ReflectUtils;
import com.mickey.mybatis.mapper.Mapper;
import com.mickey.mybatis.mapper.service.impl.base.AbstractService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

/**
 * @author J·K
 * @Description: Service
 * @date 2020/3/24 2:36 下午
 */
@Slf4j
public class BaseService<T extends BasePo, M extends Mapper<T>> extends AbstractService<T, M> {

    @Override
    public T selectById(Integer primaryKey) {
        T t = this.getInstance();
        ReflectUtils.SetPrimaryKey(t, primaryKey);
        return super.selectOne(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Integer primaryKey) {
        T t = this.getInstance();
        ReflectUtils.SetPrimaryKey(t, primaryKey);
        return super.delete(t);
    }

    @Override
    public int deleteById(List<Integer> primaryKeys) {
        List<T> list = Lists.newArrayList();
        primaryKeys.forEach(x->{
            T t = this.getInstance();
            ReflectUtils.SetPrimaryKey(t, x);
            list.add(t);
        });
        return deleteList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteLogicById(Integer primaryKey) {
        T t = this.getInstance();
        ReflectUtils.SetDelVal(t, primaryKey);
        return this.update(t);
    }

    @Override
    public int deleteLogicById(List<Integer> primaryKeys) {
        List<T> list = Lists.newArrayList();
        primaryKeys.forEach(x->{
            T t = this.getInstance();
            ReflectUtils.SetDelVal(t, x);
            list.add(t);
        });
        return updateList(list);
    }

    @SneakyThrows
    private T getInstance(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class clazz = (Class<T>) type.getActualTypeArguments()[0];
        return  (T) clazz.newInstance();
    }
}
