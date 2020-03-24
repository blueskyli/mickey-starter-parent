package com.mickey.mybatis.po.service.impl;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.po.service.impl.base.AbstractCrudServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 传统po方式调用
 * @author J·K
 * @date 2020/3/20 5:37 下午
 * @param <T>
 */
@Slf4j
public class BaseService<T extends BasePo>
        extends AbstractCrudServiceImpl<T>
{
}
