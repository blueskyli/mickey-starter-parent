package com.mickey.mybatis.plus.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mickey.core.exception.NoveSystemException;
import com.mickey.model.po.BasePo;
import com.mickey.model.po.ReflectUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author J·K
 * @description: IBaseService
 * @date 2021/5/31 10:58 上午
 */
public interface IBaseService<T extends BasePo> extends IService<T> {

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    default Integer insert(T entity) {
        boolean result = save(entity);
        return ReflectUtils.RetId(entity, result ? 1 : 0);
    }

    /**
     * 批量插入数据
     *
     * @param list
     * @return
     */
    Integer insertList(List<T> list);

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    default Integer update(T entity) {
        boolean result = updateById(entity);
        return result ? 1 : 0;
    }

    /**
     * 移除不按id修改/删除方法
     *
     * @param columnMap
     * @return
     */
    @Deprecated
    @Override
    default boolean removeByMap(Map<String, Object> columnMap) {
        throw new NoveSystemException("此方法存在隐患，请更换其他方法");
    }

    /**
     * 移除不按id修改/删除方法
     *
     * @param queryWrapper
     * @return
     */
    @Deprecated
    @Override
    default boolean remove(Wrapper<T> queryWrapper) {
        throw new NoveSystemException("此方法存在隐患，请更换其他方法");
    }

    /**
     * 不允许使用按条件更新的sql
     *
     * @param entity
     * @param updateWrapper
     * @return
     */
    @Deprecated
    @Override
    default boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper) {
        throw new NoveSystemException("此方法存在隐患，请更换其他方法");
    }

    /**
     * SQL已被updateById重写，勿用
     *
     * @param entity
     * @param updateWrapper
     * @return
     */
    @Deprecated
    @Override
    default boolean update(T entity, Wrapper<T> updateWrapper) {
        throw new NoveSystemException("此方法存在隐患，请更换其他方法");
    }

    /**
     * SQL已被updateById重写，勿用
     *
     * @param updateWrapper
     * @return
     */
    @Deprecated
    @Override
    default boolean update(Wrapper<T> updateWrapper) {
        return IService.super.update(updateWrapper);
    }

    /**
     * 根据主键批量更新
     *
     * @param list
     * @return
     */
    Integer updateList(List<T> list);


    /**
     * 根据查询条件查询一条数据
     *
     * @param entity
     * @return
     */
    default T selectOne(T entity) {
        return (T) getOne(new QueryWrapper(entity), false);
    }

    /**
     * 根据条件查询列表
     *
     * @param entity
     * @return
     */
    default List<T> selectList(T entity) {
        return list(new QueryWrapper(entity));
    }

    /**
     * 根据条件查询列表
     * @param queryWrapper
     * @return
     */
    default List<T> selectList(Wrapper<T> queryWrapper) {
        return list(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param <E>
     * @return
     */
    default <E extends IPage<T>> E selectList(E page) {
        return page(page);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param queryWrapper
     * @param <E>
     * @return
     */
    default <E extends IPage<T>> E selectList(E page, Wrapper<T> queryWrapper) {
        return page(page, queryWrapper);
    }

    /**
     * 根据主键数组查询对象实体
     *
     * @param list 主键数数组
     * @return
     */
    default List<T> selectListByIds(List<Integer> list) {
        return listByIds(list);
    }
}
