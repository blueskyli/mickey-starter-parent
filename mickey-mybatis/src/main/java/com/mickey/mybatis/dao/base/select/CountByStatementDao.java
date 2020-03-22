package com.mickey.mybatis.dao.base.select;

/**
 * @author J·K
 * @Description: CountByStatementDao
 * @date 2020/3/22 11:06 上午
 */
public interface CountByStatementDao {
    /**
     * 查询统计
     *
     * @param statementPostfix 自定义方法
     * @param entity           PO对象
     * @return
     */
    <E> Integer count(String statementPostfix, E entity);
}
