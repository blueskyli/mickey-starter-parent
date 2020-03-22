package com.mickey.mybatis.dao.base.select;

/**
 * @author J·K
 * @Description: SelectOneByStatementDao
 * @date 2020/3/22 11:07 上午
 */
public interface SelectOneByStatementDao {
    /**
     * 自定义查询数据
     *
     * @param statementPostfix 自定义方法名称
     * @param entity           数据对象
     * @return
     */
    <E> E selectOne(String statementPostfix, E entity);
}
