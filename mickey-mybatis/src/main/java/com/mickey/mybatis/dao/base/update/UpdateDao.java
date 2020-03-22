package com.mickey.mybatis.dao.base.update;

import com.mickey.model.po.BasePo;

import java.util.List;

/**
 * @author J·K
 * @Description: UpdateDao
 * @date 2020/3/22 4:26 下午
 */
public interface UpdateDao<T extends BasePo> {
    /**
     * 更新对象(此方法只根据主键ID进行更新)
     *
     * @param entity
     * @return @
     */
    Integer update(T entity);
    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    Integer update(List<T> list);
}
