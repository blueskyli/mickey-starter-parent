package com.mickey.mybatis.plus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mickey.model.po.BasePo;
import com.mickey.model.po.ReflectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     * @return @
     */
    @Transactional(rollbackFor = Exception.class)
    default Integer insertList(List<T> list) {
        boolean result = saveBatch(list);
        return result ? list.size() : 0;
    }

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
     * 批量更新
     *
     * @param list
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    default Integer updateList(List<T> list) {
        boolean result = updateBatchById(list);
        return result ? list.size() : 0;
    }

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
     * 根据主键数组查询对象实体
     *
     * @param list 主键数数组
     * @return
     */
    default List<T> selectListByIds(List<Integer> list) {
        return listByIds(list);
    }
}
