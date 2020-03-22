package com.mickey.mybatis.dao.base.select;

import com.mickey.model.po.BasePo;

/**
 * @author J·K
 * @Description: SelectDao
 * @date 2020/3/22 11:05 上午
 */
public interface SelectDao<T extends BasePo>
        extends CountDao<T>, SelectOneDao<T>, SelectListDao<T> {
}
