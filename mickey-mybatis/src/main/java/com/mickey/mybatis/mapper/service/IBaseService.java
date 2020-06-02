package com.mickey.mybatis.mapper.service;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.po.service.base.select.SelectListAndCountService;
import com.mickey.mybatis.po.service.base.delete.DeleteService;
import com.mickey.mybatis.po.service.base.insert.InsertService;
import com.mickey.mybatis.po.service.base.select.SelectService;
import com.mickey.mybatis.po.service.base.update.UpdateService;

/**
 * @author J·K
 * @Description: 提供此类给xml的namespace为mapper路径使用，新版本
 * @date 2020/3/22 11:26 上午
 */
public interface IBaseService<T extends BasePo>
    extends InsertService<T>, DeleteService<T>, UpdateService<T>, SelectService<T>, SelectListAndCountService<T> {

    /**
     * 根据主键查询实体
     * @param primaryKey
     * @return
     */
    T selectByPrimaryKey(Integer primaryKey);
    /**
     * 根据主键删除数据
     * @param primaryKey
     * @return
     */
    @Deprecated
    int deleteByPrimaryKey(Integer primaryKey);
    /**
     * 根据主键批量删除数据
     * @param primaryKeys
     * @return
     */
    @Deprecated
    int deleteByPrimaryKey(Integer[] primaryKeys);

    /**
     * 根据主键逻辑删除数据 del_Flag = true
     * @param primaryKey
     * @return
     */
    int deleteLogicByPrimaryKey(Integer primaryKey);
    /**
     * 根据主键批量逻辑删除数据 del_Flag = true
     * @param primaryKeys
     * @return
     */
    int deleteLogicByPrimaryKey(Integer[] primaryKeys);
}
