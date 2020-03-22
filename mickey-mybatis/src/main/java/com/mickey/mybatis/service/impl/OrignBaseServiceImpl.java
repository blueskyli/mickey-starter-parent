package com.mickey.mybatis.service.impl;

import com.mickey.core.exception.NoveSystemException;
import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.annotation.Id;
import com.mickey.model.page.QueryResult;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.IBaseDao;
import com.mickey.mybatis.dao.impl.BaseDao;
import com.mickey.mybatis.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @Description: 传统po方式调用
 * @author J·K
 * @date 2020/3/20 5:37 下午
 * @param <T>
 */
@Deprecated
@Slf4j
public class OrignBaseServiceImpl<T extends BasePo> implements BaseService<T>
{

    @Autowired private Map<String, BaseDao> map;

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
    public Integer count(T entity, IDataSource... args) {
        return this.getBaseDao(args).count(entity);
    }

    @Override
    public <E> Integer count(String statementPostfix, E entity, IDataSource... args) {
        return this.getBaseDao(args).count(statementPostfix, entity);
    }

    @Override
    public T selectOne(T entity, IDataSource... args) {
        return (T) this.getBaseDao(args).selectOne(entity);
    }

    @Override
    public <E> E selectOne(String statementPostfix, E entity, IDataSource... args) {
        return (E) this.getBaseDao(args).selectOne(statementPostfix, entity);
    }

    @Override
    @Transactional
    public <E> int insert(String statementPostfix, E entity, IDataSource... args) {
        int effectRow = this.getBaseDao(args).insert(statementPostfix, entity);
        return RetIdOrEffectRow(entity, effectRow);
    }

    @Override
    @Transactional
    public int insert(T entity, IDataSource... args) {
        int effectRow = this.getBaseDao(args).insert(entity);
        return RetIdOrEffectRow(entity, effectRow);
    }

    @Override
    @Transactional
    public int insertList(List<T> list, IDataSource... args) {
        return this.getBaseDao(args).insert(list);
    }

    @Override
    @Transactional
    public <E> int insertList(String statementPostfix, List<E> list, IDataSource... args) {
        return this.getBaseDao(args).insert(statementPostfix, list);
    }

    @Override
    @Transactional
    public int update(T entity, IDataSource... args) {
        return this.getBaseDao(args).update(entity);
    }

    @Override
    @Transactional
    public <E> int update(String statementPostfix, E entity, IDataSource... args) {
        return this.getBaseDao(args).update(statementPostfix, entity);
    }

    @Override
    @Transactional
    public int updateList(List<T> list, IDataSource... args) {
        return this.getBaseDao(args).update(list);
    }

    @Override
    @Transactional
    public <E> int updateList(String statementPostfix, List<E> list, IDataSource... args) {
        return this.getBaseDao(args).update(statementPostfix, list);
    }

    @Override
    @Transactional
    public int delete(T entity, IDataSource... args) {
        return this.getBaseDao(args).delete(entity);
    }

    @Override
    @Transactional
    public <E> int delete(String statementPostfix, E entity, IDataSource... args) {
        return this.getBaseDao(args).delete(statementPostfix, entity);
    }

    @Override
    @Transactional
    public int deleteList(List<T> list, IDataSource... args) {
        return this.getBaseDao(args).delete(list);
    }

    @Override
    @Transactional
    public <E> int deleteList(String statementPostfix, List<E> list, IDataSource... args) {
        return this.getBaseDao(args).delete(statementPostfix, list);
    }

    @Override
    public List<T> selectList(T entity, IDataSource... args) {
        return this.getBaseDao(args).selectList(entity);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, IDataSource... args) {
        return this.getBaseDao(args).selectList(statementPostfix, entity);
    }

    @Override
    public List<T> selectList(T entity, String orderBy, IDataSource... args) {
        return this.getBaseDao(args).selectList(entity, orderBy);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, String orderBy, IDataSource... args) {
        return this.getBaseDao(args).selectList(statementPostfix, entity, orderBy);
    }

    @Override
    public List<T> selectList(T entity, int pageNum, int pageSize, IDataSource... args) {
        return this.getBaseDao(args).selectList(entity, pageNum, pageSize);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, int pageNum, int pageSize, IDataSource... args) {
        return this.getBaseDao(args).selectList(statementPostfix, entity, pageNum, pageSize);
    }

    @Override
    public List<T> selectList(T entity, int pageNum, int pageSize, String orderBy, IDataSource... args) {
        return this.getBaseDao(args).selectList(entity, pageNum, pageSize, orderBy);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, IDataSource... args) {
        return this.getBaseDao(args).selectList(statementPostfix, entity, pageNum, pageSize, orderBy);
    }

    @Override
    public <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, IDataSource... args) {
        return this.getBaseDao(args).selectListAndCount(statementPostfix, entity, pageNum, pageSize, orderBy);
    }

    @Override
    public <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, String statementCount, IDataSource... args) {
        return this.getBaseDao(args).selectListAndCount(statementPostfix, entity, pageNum, pageSize, orderBy, statementCount);
    }

    private <E> Integer RetIdOrEffectRow(E entity, int effectRow) {
        if (entity instanceof BasePo) {
            Class<?> cls = entity.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Id annotation = field.getAnnotation(Id.class);
                if (annotation != null) {
                    try {
                        return Integer.parseInt(field.get(entity).toString());
                    } catch (Exception e) {
                        log.error("获取主键值报错", e);
                        return effectRow;
                    }
                }
            }
        }
        return effectRow;
    }
}
