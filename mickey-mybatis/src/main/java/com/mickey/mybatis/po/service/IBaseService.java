package com.mickey.mybatis.po.service;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.po.service.base.BaseDeleteService;
import com.mickey.mybatis.po.service.base.BaseInsertService;
import com.mickey.mybatis.po.service.base.BaseSelectService;
import com.mickey.mybatis.po.service.base.BaseUpdateService;

/**
 * @author J·K
 * @Description: 提供此类给xml的namespace为po路径使用，兼容之前写法
 * @date 2020/3/20 5:11 下午
 */
public interface IBaseService<T extends BasePo>
    extends BaseInsertService<T>, BaseDeleteService<T>, BaseUpdateService<T>, BaseSelectService<T> {
}
