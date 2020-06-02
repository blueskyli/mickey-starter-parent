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
public class BaseService<T extends BasePo, M extends Mapper> extends AbstractService<T, M> {

    @Override
    public T selectByPrimaryKey(Integer primaryKey) {
        T t = this.getInstance();
        ReflectUtils.SetPrimaryKey(t, primaryKey);
        return super.selectOne(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer primaryKey) {
        T t = this.getInstance();
        ReflectUtils.SetPrimaryKey(t, primaryKey);
        return super.delete(t);
    }

    @Override
    public int deleteByPrimaryKey(Integer[] primaryKeys) {
        List<T> list = Lists.newArrayList();
        Arrays.stream(primaryKeys).forEach(x->{
            T t = this.getInstance();
            ReflectUtils.SetPrimaryKey(t, x);
            list.add(t);
        });
        return deleteList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteLogicByPrimaryKey(Integer primaryKey) {
        T t = this.getInstance();
        ReflectUtils.SetDelVal(t, primaryKey);
        return this.update(t);
    }

    @Override
    public int deleteLogicByPrimaryKey(Integer[] primaryKeys) {
        List<T> list = Lists.newArrayList();
        Arrays.stream(primaryKeys).forEach(x->{
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
