package com.mickey.mybatis.po.service;

import com.google.common.collect.Lists;
import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.po.service.base.BaseDeleteService;
import com.mickey.mybatis.po.service.base.BaseInsertService;
import com.mickey.mybatis.po.service.base.BaseSelectService;
import com.mickey.mybatis.po.service.base.BaseUpdateService;

import java.util.List;

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
    T selectById(Integer primaryKey, IDataSource... args);
    /**
     * 根据主键删除数据
     * @param primaryKey
     * @param args
     * @return
     */
    @Deprecated
    int deleteById(Integer primaryKey, IDataSource... args);
    /**
     * 根据主键批量删除数据
     * @param primaryKeys
     * @return
     */
    @Deprecated
    default int deleteById(Integer[] primaryKeys, IDataSource... args){
        return this.deleteById(Lists.newArrayList(primaryKeys),args);
    }
    /**
     * 根据主键批量删除数据
     * @param primaryKeys
     * @return
     */
    @Deprecated
    int deleteById(List<Integer> primaryKeys, IDataSource... args);

    /**
     * 根据主键逻辑删除数据 del_Flag = true
     * @param primaryKey
     * @param args
     * @return
     */
    int deleteLogicById(Integer primaryKey, IDataSource... args);
    /**
     * 根据主键批量逻辑删除数据 del_Flag = true
     * @param primaryKeys
     * @return
     */
    int deleteLogicById(List<Integer> primaryKeys, IDataSource... args);
    /**
     * 根据主键批量逻辑删除数据 del_Flag = true
     * @param primaryKeys
     * @return
     */
    default int deleteLogicById(Integer[] primaryKeys, IDataSource... args){
        return this.deleteLogicById(Lists.newArrayList(primaryKeys),args);
    }
}
