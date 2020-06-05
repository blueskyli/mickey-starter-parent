package com.mickey.mybatis.po.service.impl;

import com.google.common.collect.Lists;
import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.po.BasePo;
import com.mickey.model.po.ReflectUtils;
import com.mickey.mybatis.po.service.impl.base.AbstractCrudServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

/**
 * @param <T>
 * @author J·K
 * @Description: 传统po方式调用
 * @date 2020/3/20 5:37 下午
 */
@Slf4j
public class BaseService<T extends BasePo>
    extends AbstractCrudServiceImpl<T> {

    @SneakyThrows
    @Override
    public T selectById(Integer primaryKey, IDataSource... args) {
        T t = this.getInstance();
        ReflectUtils.SetPrimaryKey(t, primaryKey);
        return super.selectOne(t, args);
    }

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Integer primaryKey, IDataSource... args) {
        T t = this.getInstance();
        ReflectUtils.SetPrimaryKey(t, primaryKey);
        return super.delete(t,args);
    }

    @Override
    public int deleteById(Integer[] primaryKeys, IDataSource... args) {
        List<T> list = Lists.newArrayList();
        Arrays.stream(primaryKeys).forEach(x->{
            T t = this.getInstance();
            ReflectUtils.SetPrimaryKey(t, x);
            list.add(t);
        });
        return deleteList(list,args);
    }

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteLogicById(Integer primaryKey, IDataSource... args) {
        T t = this.getInstance();
        ReflectUtils.SetDelVal(t, primaryKey);
        return this.update(t,args);
    }

    @Override
    public int deleteLogicById(Integer[] primaryKeys, IDataSource... args) {
        List<T> list = Lists.newArrayList();
        Arrays.stream(primaryKeys).forEach(x->{
            T t = this.getInstance();
            ReflectUtils.SetDelVal(t, x);
            list.add(t);
        });
        return updateList(list,args);
    }

    @SneakyThrows
    private T getInstance(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class clazz = (Class<T>) type.getActualTypeArguments()[0];
        return  (T) clazz.newInstance();
    }
}
