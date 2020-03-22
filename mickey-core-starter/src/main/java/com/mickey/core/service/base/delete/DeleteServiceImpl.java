package com.mickey.core.service.base.delete;

import com.mickey.core.base.annotation.IDataSource;
import com.mickey.core.base.exception.NoveSystemException;
import com.mickey.core.base.po.BasePo;
import com.mickey.core.dao.base.BaseDao;
import com.mickey.core.dao.base.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author J·K
 * @Description: DeleteServiceImpl
 * @date 2020/3/22 11:37 上午
 */
public class DeleteServiceImpl<T extends BasePo> implements DeleteService<T> {
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
    public int delete(T entity, IDataSource... args) {
        return this.getBaseDao(args).delete(entity);
    }

    @Override
    @Transactional
    public int deleteList(List<T> list, IDataSource... args) {
        return this.getBaseDao(args).delete(list);
    }
}
