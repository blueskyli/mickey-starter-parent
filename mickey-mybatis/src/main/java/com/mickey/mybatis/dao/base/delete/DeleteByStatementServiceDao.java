package com.mickey.mybatis.dao.base.delete;

/**
 * @author J·K
 * @Description: DeleteByStatementServiceDao
 * @date 2020/3/22 4:23 下午
 */
public interface DeleteByStatementServiceDao {
    /**
     * 自定义删除方法
     *
     * @param statementPostfix
     * @param entity
     * @return @
     */
    <E> Integer delete(String statementPostfix, E entity);
}
