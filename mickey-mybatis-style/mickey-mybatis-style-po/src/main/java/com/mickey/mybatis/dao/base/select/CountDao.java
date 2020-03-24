package com.mickey.mybatis.dao.base.select;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.base.select.CountMapper;

/**
 * @author J·K
 * @Description: CountDao
 * @date 2020/3/22 11:06 上午
 */
public interface CountDao<T extends BasePo> extends CountMapper<T> {
}
