package com.mickey.mybatis.service;

import com.mickey.model.page.QueryResult;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.service.base.delete.DeleteService;
import com.mickey.mybatis.service.base.insert.InsertService;
import com.mickey.mybatis.service.base.select.SelectService;
import com.mickey.mybatis.service.base.update.UpdateService;

/**
 * @author J·K
 * @Description: 提供此类给xml的namespace为mapper路径使用，新版本
 * @date 2020/3/22 11:26 上午
 */
public interface Service<T extends BasePo>
        extends InsertService<T>, DeleteService<T>, UpdateService<T>, SelectService<T> {

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
