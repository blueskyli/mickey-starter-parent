package com.mickey.mybatis.mapper.service.impl;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.mapper.Mapper;
import com.mickey.mybatis.mapper.service.impl.base.AbstractService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author J·K
 * @Description: Service
 * @date 2020/3/24 2:36 下午
 */
@Slf4j
public class BaseService<T extends BasePo,M extends Mapper> extends AbstractService<T,M> {
}
