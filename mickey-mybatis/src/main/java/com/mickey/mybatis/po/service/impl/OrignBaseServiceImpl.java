package com.mickey.mybatis.po.service.impl;

import com.google.common.collect.Lists;
import com.mickey.core.exception.NoveSystemException;
import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.page.QueryResult;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.IBaseDao;
import com.mickey.mybatis.po.service.IBaseService;
import com.mickey.model.po.ReflectUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @param <T>
 * @author J·K
 * @Description: 传统po方式调用
 * @date 2020/3/20 5:37 下午
 */
@Deprecated
@Slf4j
public class OrignBaseServiceImpl<T extends BasePo> implements IBaseService<T> {

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
    public T selectById(Integer primaryKey, IDataSource... args) {
        T t = this.getInstance();
        ReflectUtils.SetPrimaryKey(t, primaryKey);
        return this.selectOne(t, args);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Integer primaryKey, IDataSource... args) {
        T t = this.getInstance();
        ReflectUtils.SetPrimaryKey(t, primaryKey);
        return this.delete(t,args);
    }

    @Override
    public int deleteById(Integer[] primaryKeys, IDataSource... args) {
        List<T> list = Lists.newArrayList();
        Arrays.stream(primaryKeys).forEach(x->{
            T t = this.getInstance();
            ReflectUtils.SetPrimaryKey(t, x);
            list.add(t);
        });
        return deleteList(list,args);
    }

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteLogicById(Integer primaryKey, IDataSource... args) {
        T t = this.getInstance();
        ReflectUtils.SetDelVal(t, primaryKey);
        return this.update(t,args);
    }

    @Override
    public int deleteLogicById(Integer[] primaryKeys, IDataSource... args) {
        List<T> list = Lists.newArrayList();
        Arrays.stream(primaryKeys).forEach(x->{
            T t = this.getInstance();
            ReflectUtils.SetDelVal(t, x);
            list.add(t);
        });
        return updateList(list,args);
    }

    @Override
    public <E> E selectOne(String statementPostfix, E entity, IDataSource... args) {
        return (E) this.getBaseDao(args).selectOne(statementPostfix, entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int insert(String statementPostfix, E entity, IDataSource... args) {
        int effectRows = this.getBaseDao(args).insert(statementPostfix, entity);
        return ReflectUtils.RetId(entity, effectRows);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(T entity, IDataSource... args) {
        int effectRows = this.getBaseDao(args).insert(entity);
        return ReflectUtils.RetId(entity, effectRows);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertList(List<T> list, IDataSource... args) {
        return this.getBaseDao(args).insertList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int insertList(String statementPostfix, List<E> list, IDataSource... args) {
        return this.getBaseDao(args).insert(statementPostfix, list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(T entity, IDataSource... args) {
        return this.getBaseDao(args).update(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int update(String statementPostfix, E entity, IDataSource... args) {
        return this.getBaseDao(args).update(statementPostfix, entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateList(List<T> list, IDataSource... args) {
        return this.getBaseDao(args).updateList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int updateList(String statementPostfix, List<E> list, IDataSource... args) {
        return this.getBaseDao(args).update(statementPostfix, list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(T entity, IDataSource... args) {
        return this.getBaseDao(args).delete(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <E> int delete(String statementPostfix, E entity, IDataSource... args) {
        return this.getBaseDao(args).delete(statementPostfix, entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteList(List<T> list, IDataSource... args) {
        return this.getBaseDao(args).deleteList(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    public QueryResult<T> selectListAndCount(T entity, Integer pageNum, Integer pageSize, String orderBy, IDataSource... args) {
        return this.getBaseDao(args).selectListAndCount(entity, pageNum, pageSize, orderBy);
    }

    @Override
    public <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, IDataSource... args) {
        return this.getBaseDao(args).selectListAndCount(statementPostfix, entity, pageNum, pageSize, orderBy);
    }

    @Override
    public <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, String statementCount, IDataSource... args) {
        return this.getBaseDao(args).selectListAndCount(statementPostfix, entity, pageNum, pageSize, orderBy, statementCount);
    }

    @SneakyThrows
    private T getInstance(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class clazz = (Class<T>) type.getActualTypeArguments()[0];
        return  (T) clazz.newInstance();
    }
}
