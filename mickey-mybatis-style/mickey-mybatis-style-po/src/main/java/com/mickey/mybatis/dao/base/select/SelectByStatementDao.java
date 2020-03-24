package com.mickey.mybatis.dao.base.select;

/**
 * @author J·K
 * @Description: SelectByStatementDao
 * @date 2020/3/22 11:09 上午
 */
public interface SelectByStatementDao
        extends
        CountByStatementDao,
        SelectOneByStatementDao,
        SelectListByStatementDao,
        SelectListAndCountByStatementDao {
}
