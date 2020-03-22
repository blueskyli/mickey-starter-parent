package com.mickey.mybatis.service;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.service.base.BaseDeleteService;
import com.mickey.mybatis.service.base.BaseInsertService;
import com.mickey.mybatis.service.base.BaseSelectService;
import com.mickey.mybatis.service.base.BaseUpdateService;

/**
 * @Description: 提供此类给xml的namespace为po路径使用，兼容之前写法
 * @author J·K
 * @date 2020/3/20 5:11 下午
 */
public interface BaseService<T extends BasePo>
        extends BaseInsertService<T>, BaseDeleteService<T>, BaseUpdateService<T>, BaseSelectService<T>
{
}
