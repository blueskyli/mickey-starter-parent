package com.mickey.core.service.base.select;

import com.mickey.core.base.annotation.IDataSource;

/**
 * @author J·K
 * @Description: SelectOneByStatementService
 * @date 2020/3/22 11:07 上午
 */
public interface SelectOneByStatementService {
    /**
     * 根据查询条件查询一条数据
     *
     * @param statementPostfix
     * @param entity
     * @param args
     * @return
     */
    <E> E selectOne(String statementPostfix, E entity, IDataSource... args);
}
