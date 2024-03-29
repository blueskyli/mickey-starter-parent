package com.mickey.mybatis.po.service.impl.base.insert;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.po.service.impl.base.delete.DeleteServiceImpl;
import com.mickey.model.po.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author J·K
 * @Description: InsertServiceImpl
 * @date 2020/3/22 3:41 下午
 */
@Slf4j
public abstract class InsertServiceImpl<T extends BasePo> extends DeleteServiceImpl<T> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(T entity, IDataSource... args) {
        int effectRows = super.getBaseDao(args).insert(entity);
        return ReflectUtils.RetId(entity, effectRows);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertList(List<T> list, IDataSource... args) {
        return super.getBaseDao(args).insertList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int insert(String statementPostfix, E entity, IDataSource... args) {
        int effectRow = super.getBaseDao(args).insert(statementPostfix, entity);
        return ReflectUtils.RetId(entity, effectRow);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int insertList(String statementPostfix, List<E> list, IDataSource... args) {
        return super.getBaseDao(args).insert(statementPostfix, list);
    }
}
