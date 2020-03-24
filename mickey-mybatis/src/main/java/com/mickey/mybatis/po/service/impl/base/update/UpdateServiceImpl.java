package com.mickey.mybatis.po.service.impl.base.update;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.po.service.impl.base.select.SelectServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author J·K
 * @Description: UpdateServiceImpl
 * @date 2020/3/22 3:47 下午
 */
public abstract class UpdateServiceImpl<T extends BasePo> extends SelectServiceImpl<T> {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(T entity, IDataSource... args) {
        return super.getBaseDao(args).update(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateList(List<T> list, IDataSource... args) {
        return super.getBaseDao(args).updateList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int update(String statementPostfix, E entity, IDataSource... args) {
        return super.getBaseDao(args).update(statementPostfix, entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int updateList(String statementPostfix, List<E> list, IDataSource... args) {
        return super.getBaseDao(args).update(statementPostfix, list);
    }

}
