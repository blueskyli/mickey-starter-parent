package com.mickey.core.service.base.delete;

import com.mickey.core.base.annotation.IDataSource;
import com.mickey.core.base.exception.NoveSystemException;
import com.mickey.core.dao.base.BaseDao;
import com.mickey.core.dao.base.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author J·K
 * @Description: DeleteByStatementServiceImpl
 * @date 2020/3/22 11:36 上午
 */
public class DeleteByStatementServiceImpl implements DeleteByStatementService {
    @Autowired
    private Map<String, BaseDao> map;

    public IBaseDao getBaseDao(IDataSource... args)
    {
        if(args.length == 0)
            return map.get("baseDao");
        else
        {
            IBaseDao baseDao = map.get(args[0].getBaseDao());
            if(baseDao == null)
                throw new NoveSystemException("500", "未知数据源");
            return baseDao;
        }
    }

    @Override
    @Transactional
    public <E> int delete(String statementPostfix, E entity, IDataSource... args) {
        return this.getBaseDao(args).delete(statementPostfix, entity);
    }

    @Override
    @Transactional
    public <E> int deleteList(String statementPostfix, List<E> list, IDataSource... args) {
        return this.getBaseDao(args).delete(statementPostfix, list);
    }
}
