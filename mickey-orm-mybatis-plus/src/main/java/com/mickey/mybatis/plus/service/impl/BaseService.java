package com.mickey.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.plus.service.IBaseService;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;

/**
 * @author J·K
 * @description: BaseService
 * @date 2021/5/31 10:59 上午
 */
public class BaseService<T extends BasePo, M extends BaseMapper<T>> extends ServiceImpl<M, T> implements IBaseService<T> {

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
}
