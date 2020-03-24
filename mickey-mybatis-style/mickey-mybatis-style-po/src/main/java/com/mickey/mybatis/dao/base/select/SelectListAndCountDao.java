package com.mickey.mybatis.dao.base.select;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.page.QueryResult;
import com.mickey.model.po.BasePo;

/**
 * @author J·K
 * @Description: SelectListAndCountDao
 * @date 2020/3/22 11:08 上午
 */
public interface SelectListAndCountDao<T extends BasePo> {
    /**
     * 此方法返回数据结果集和count
     *
     * @param entity           参数对象
     * @param pageNum          页码
     * @param pageSize         每页记录数
     * @param orderBy          排序字段(例：'id desc')
     * @return
     */
    QueryResult<T> selectListAndCount(T entity, Integer pageNum, Integer pageSize, String orderBy);
}
