package com.mickey.mybatis.dao.base.insert;

/**
 * @author J·K
 * @Description: InsertByStatementDao
 * @date 2020/3/22 4:25 下午
 */
public interface InsertByStatementDao {
    /**
     * 插入对象
     *
     * @param statementPostfix
     * @param entity
     * @return @
     */
    <E> Integer insert(String statementPostfix, E entity);
}
