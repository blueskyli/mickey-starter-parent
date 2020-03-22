package com.mickey.core.service.base.delete;

import com.mickey.core.base.annotation.IDataSource;

import java.util.List;

/**
 * @author J·K
 * @Description: DeleteByStatementService
 * @date 2020/3/22 10:57 上午
 */
public interface DeleteByStatementService{
    /**
     * 删除
     *
     * @param statementPostfix
     * @param entity
     * @param args
     * @param <E>
     * @return
     */
    @Deprecated
    <E> int delete(String statementPostfix, E entity, IDataSource... args);

    /**
     * 批量删除
     *
     * @param statementPostfix
     * @param list
     * @param args
     * @param <E>
     * @return
     */
    @Deprecated
    <E> int deleteList(String statementPostfix, List<E> list, IDataSource... args);
}
