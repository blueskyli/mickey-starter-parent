package com.mickey.mybatis.mapper.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.base.update.UpdateMapper;

/**
 * @author J·K
 * @Description: BaseUpdateMapper
 * @date 2020/3/22 9:56 上午
 */
public interface BaseUpdateMapper<T extends BasePo> extends UpdateMapper<T> {
}
