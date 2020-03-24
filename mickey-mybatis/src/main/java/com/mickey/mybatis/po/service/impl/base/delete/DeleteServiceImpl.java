package com.mickey.mybatis.po.service.impl.base.delete;

import com.mickey.core.exception.NoveSystemException;
import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.IBaseDao;
import com.mickey.mybatis.po.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author J·K
 * @Description: DeleteServiceImpl
 * @date 2020/3/22 11:37 上午
 */
public abstract class DeleteServiceImpl<T extends BasePo> implements IBaseService<T> {

    @Autowired
    private Map<String, IBaseDao> map;

    public IBaseDao getBaseDao(IDataSource... args) {
        if (args.length == 0)
            return map.get("baseDao");
        else {
            IBaseDao baseDao = map.get(args[0].getBaseDao());
            if (baseDao == null)
                throw new NoveSystemException("500", "未知数据源");
            return baseDao;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(T entity, IDataSource... args) {
        return this.getBaseDao(args).delete(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteList(List<T> list, IDataSource... args) {
        return this.getBaseDao(args).deleteList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int delete(String statementPostfix, E entity, IDataSource... args) {
        return this.getBaseDao(args).delete(statementPostfix, entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int deleteList(String statementPostfix, List<E> list, IDataSource... args) {
        return this.getBaseDao(args).delete(statementPostfix, list);
    }
}
