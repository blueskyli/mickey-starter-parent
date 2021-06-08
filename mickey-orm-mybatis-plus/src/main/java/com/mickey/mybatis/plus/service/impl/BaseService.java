package com.mickey.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.plus.mapper.Mapper;
import com.mickey.mybatis.plus.service.IBaseService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author J·K
 * @description: BaseService
 * @date 2021/5/31 10:59 上午
 */
public class BaseService<T extends BasePo, M extends Mapper<T>> extends ServiceImpl<M, T> implements IBaseService<T> {

    @Autowired
    private M mapper;

    @SneakyThrows
    @Override
    protected Class<T> currentMapperClass() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[1];
    }

    @SneakyThrows
    @Override
    protected Class<T> currentModelClass() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertList(List<T> list) {
        return this.mapper.insertList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateList(List<T> list) {
        return this.mapper.updateList(list);
    }
}
