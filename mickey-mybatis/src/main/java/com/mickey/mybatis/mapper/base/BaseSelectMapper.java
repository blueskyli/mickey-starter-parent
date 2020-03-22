package com.mickey.mybatis.mapper.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.base.select.CountDao;
import com.mickey.mybatis.dao.base.select.SelectOneDao;

import java.util.List;

/**
 * @author J·K
 * @Description: BaseSelectMapper
 * @date 2020/3/22 9:54 上午
 */
public interface BaseSelectMapper<T extends BasePo>
        extends
        CountDao<T>,
        SelectOneDao<T> {

    /**
     * 查询一个列表， 不分页,不排序
     *
     * @param entity 实体对象
     * @return
     */
    List<T> selectList(T entity);

}
