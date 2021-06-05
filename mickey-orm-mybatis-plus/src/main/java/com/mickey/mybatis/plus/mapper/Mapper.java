package com.mickey.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mickey.core.exception.NoveSystemException;
import com.mickey.model.po.BasePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author J·K
 * @description: Mapper
 * @date 2021/5/31 11:09 上午
 */
public interface Mapper<T extends BasePo> extends BaseMapper<T> {

    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer insertList(@Param("list") List<T> list);

    /**
     * 根据主键批量更新
     * @param list
     * @return
     */
    Integer updateList(@Param("list") List<T> list);

    /**
     * 存在隐患，禁止使用
     * @param entity
     * @param updateWrapper
     * @return
     */
    @Deprecated
    @Override
    default int update(T entity, Wrapper<T> updateWrapper){
        throw new NoveSystemException("此方法存在隐患，请更换其他方法");
    }

    /**
     * 存在隐患，禁止使用
     * @param columnMap
     * @return
     */
    @Deprecated
    @Override
    default int deleteByMap(Map<String, Object> columnMap){
        throw new NoveSystemException("此方法存在隐患，请更换其他方法");
    }

    /**
     * 存在隐患，禁止使用
     * @param queryWrapper
     * @return
     */
    @Deprecated
    @Override
    default int delete(Wrapper<T> queryWrapper){
        throw new NoveSystemException("此方法存在隐患，请更换其他方法");
    }
}
