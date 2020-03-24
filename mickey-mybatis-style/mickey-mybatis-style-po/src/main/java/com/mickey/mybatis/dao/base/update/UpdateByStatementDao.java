package com.mickey.mybatis.dao.base.update;

/**
 * @author J·K
 * @Description: UpdateByStatementDao
 * @date 2020/3/22 4:27 下午
 */
public interface UpdateByStatementDao {
    /**
     * 自定义更新方法
     *
     * @param statementPostfix 方法路径名称
     * @param entity
     * @return @
     */
    <E> Integer update(String statementPostfix, E entity);
}
