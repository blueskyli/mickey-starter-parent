package com.mickey.mybatis.service.base.select;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.page.QueryResult;
import com.mickey.model.po.BasePo;

/**
 * @author J·K
 * @Description: SelectListAndCountService
 * @date 2020/3/24 11:56 上午
 */
public interface SelectListAndCountService<T extends BasePo> {
    /**
     * 此方法返回数据结果集和count
     *
     * @param entity           参数对象
     * @param pageNum          页码
     * @param pageSize         每页记录数
     * @param orderBy          排序字段(例：'id desc')
     * @return
     */
    QueryResult<T> selectListAndCount(T entity, Integer pageNum, Integer pageSize, String orderBy, IDataSource... args);
}
