package com.mickey.mybatis.service.impl;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.service.impl.base.BaseCrudServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 传统po方式调用
 * @author J·K
 * @date 2020/3/20 5:37 下午
 * @param <T>
 */
@Slf4j
public class BaseServiceImpl<T extends BasePo>
        extends BaseCrudServiceImpl<T>
{
}
