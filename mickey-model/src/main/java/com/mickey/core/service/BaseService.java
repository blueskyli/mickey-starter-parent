package com.mickey.core.service;

import com.mickey.core.base.po.BasePo;
import com.mickey.core.service.base.BaseDeleteService;
import com.mickey.core.service.base.BaseInsertService;
import com.mickey.core.service.base.BaseSelectService;
import com.mickey.core.service.base.BaseUpdateService;

/**
 * @Description: 提供此类给xml的namespace为po路径使用，兼容之前写法
 * @author J·K
 * @date 2020/3/20 5:11 下午
 */
public interface BaseService<T extends BasePo>
        extends BaseInsertService<T>, BaseDeleteService<T>,BaseUpdateService<T>, BaseSelectService<T>
{
}
