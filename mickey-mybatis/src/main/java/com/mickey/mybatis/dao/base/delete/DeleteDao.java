package com.mickey.mybatis.dao.base.delete;

import com.mickey.model.po.BasePo;

import java.util.List;

/**
 * @author J·K
 * @Description: DeleteDao
 * @date 2020/3/22 4:23 下午
 */
public interface DeleteDao<T extends BasePo> {
    /**
     * 删除
     *
     * @param entity
     * @return @
     */
    Integer delete(T entity);
    /**
     * 批量删除
     *
     * @param list
     * @return
     */
    Integer deleteList(List<T> list);
}
