package com.mickey.mybatis.po.service.impl.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.po.service.impl.base.update.UpdateServiceImpl;

/**
 * @author J·K
 * @Description: BaseDeleteServiceImpl
 * @date 2020/3/22 11:46 上午
 */
public abstract class AbstractCrudServiceImpl<T extends BasePo> extends UpdateServiceImpl<T> {
}
