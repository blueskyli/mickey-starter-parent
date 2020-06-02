package com.mickey.mybatis.po.service;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.po.service.base.BaseDeleteService;
import com.mickey.mybatis.po.service.base.BaseInsertService;
import com.mickey.mybatis.po.service.base.BaseSelectService;
import com.mickey.mybatis.po.service.base.BaseUpdateService;

/**
 * @author J·K
 * @Description: 提供此类给xml的namespace为po路径使用，兼容之前写法
 * @date 2020/3/20 5:11 下午
 */
public interface IBaseService<T extends BasePo>
    extends BaseInsertService<T>, BaseDeleteService<T>, BaseUpdateService<T>, BaseSelectService<T> {

    /**
     * 根据主键查询实体
     * @param primaryKey
     * @param args
     * @return
     */
    T selectByPrimaryKey(Integer primaryKey, IDataSource... args);
    /**
     * 根据主键删除数据
     * @param primaryKey
     * @param args
     * @return
     */
    @Deprecated
    int deleteByPrimaryKey(Integer primaryKey, IDataSource... args);
    /**
     * 根据主键批量删除数据
     * @param primaryKeys
     * @param args
     * @return
     */
    @Deprecated
    int deleteByPrimaryKey(Integer[] primaryKeys, IDataSource... args);

    /**
     * 根据主键逻辑删除数据 del_Flag = true
     * @param primaryKey
     * @param args
     * @return
     */
    int deleteLogicByPrimaryKey(Integer primaryKey, IDataSource... args);
    /**
     * 根据主键批量逻辑删除数据 del_Flag = true
     * @param primaryKeys
     * @param args
     * @return
     */
    int deleteLogicByPrimaryKey(Integer[] primaryKeys, IDataSource... args);
}
