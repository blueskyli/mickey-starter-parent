package com.mickey.mybatis.dao.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mickey.model.page.QueryResult;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.IBaseDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class BaseDao<T extends BasePo> extends SqlSessionDaoSupport implements IBaseDao<T> {

    protected static final String POSTFIX_COUNT = ".count";
    protected static final String POSTFIX_SELECTONE = ".selectOne";
    protected static final String POSTFIX_INSERT = ".insert";
    protected static final String POSTFIX_INSERTLIST = ".insertList";
    protected static final String POSTFIX_UPDATE = ".update";
    protected static final String POSTFIX_UPDATELIST = ".updateList";
    protected static final String POSTFIX_DELETE = ".delete";
    protected static final String POSTFIX_DELETELIST = ".deleteList";
    protected static final String POSTFIX_SELECTLIST = ".selectList";


    @Override
    public Integer count(T entity) {
        String className = entity.getClass().getName();
        return this.count(className + POSTFIX_COUNT, entity);
    }

    @Override
    public <E> Integer count(String statementPostfix, E entity) {
        return getSqlSession().selectOne(statementPostfix, entity);
    }

    @Override
    public T selectOne(T entity) {
        String className = entity.getClass().getName();
        return this.selectOne(className + POSTFIX_SELECTONE, entity);
    }

    @Override
    public <E> E selectOne(String statementPostfix, E entity) {
        return getSqlSession().selectOne(statementPostfix, entity);
    }

    @Override
    public Integer insert(T entity) {
        String className = entity.getClass().getName();
        return this.insert(className + POSTFIX_INSERT, entity);
    }

    @Override
    public <E> Integer insert(String statementPostfix, E entity) {
        return getSqlSession().insert(statementPostfix, entity);
    }

    @Override
    public Integer insertList(List<T> list) {
        if(CollectionUtils.isEmpty(list))
        {
            return 0;
        }
        String className = list.get(0).getClass().getName();
        return getSqlSession().insert(className + POSTFIX_INSERTLIST, list);
    }

    @Override
    public Integer update(T entity) {
        String className = entity.getClass().getName();
        return this.update(className + POSTFIX_UPDATE, entity);
    }

    @Override
    public <E> Integer update(String statementPostfix, E entity) {
        return getSqlSession().update(statementPostfix, entity);
    }

    @Override
    public Integer updateList(List<T> list) {
        if(CollectionUtils.isEmpty(list))
        {
            return 0;
        }
        String className = list.get(0).getClass().getName();
        return getSqlSession().update(className + POSTFIX_UPDATELIST, list);
    }

    @Override
    public Integer delete(T entity) {
        if(null == entity)
        {
            logger.warn("can not delete data , entity is null !!!");
            return 0;
        }
        String className = entity.getClass().getName();
        return this.delete(className + POSTFIX_DELETE, entity);
    }

    @Override
    public <E> Integer delete(String statementPostfix, E entity) {
        return getSqlSession().delete(statementPostfix, entity);
    }

    @Override
    public Integer deleteList(List<T> list) {
        if(CollectionUtils.isEmpty(list))
        {
            return 0;
        }
        String className = list.get(0).getClass().getName();
        return getSqlSession().delete(className + POSTFIX_DELETELIST, list);
    }

    @Override
    public List<T> selectList(T entity) {
        String statementPostfix = entity.getClass().getName() + POSTFIX_SELECTLIST;
        return this.selectList(statementPostfix, entity);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity) {
        return this.selectList(statementPostfix, entity, null);
    }

    @Override
    public List<T> selectList(T entity, String orderBy) {
        String statementPostfix = entity.getClass().getName() + POSTFIX_SELECTLIST;
        return this.selectList(statementPostfix, entity, orderBy);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, String orderBy) {
        if(!StringUtils.isEmpty(orderBy))
        {
            PageHelper.orderBy(orderBy);
        }
        return getSqlSession().selectList(statementPostfix, entity);
    }

    @Override
    public List<T> selectList(T entity, Integer pageNum, Integer pageSize) {
        String statementPostfix = entity.getClass().getName() + POSTFIX_SELECTLIST;
        return this.selectList(statementPostfix, entity, pageNum, pageSize);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, Integer pageNum, Integer pageSize) {
        return this.selectList(statementPostfix, entity, pageNum, pageSize,null);
    }

    @Override
    public List<T> selectList(T entity, Integer pageNum, Integer pageSize, String orderBy) {
        String statementPostfix = entity.getClass().getName() + POSTFIX_SELECTLIST;
        return this.selectList(statementPostfix, entity, pageNum, pageSize, orderBy);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, Integer pageNum, Integer pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize, false);
        if(!StringUtils.isEmpty(orderBy))
        {
            PageHelper.orderBy(orderBy);
        }
        return getSqlSession().selectList(statementPostfix, entity);
    }

    @Override
    public QueryResult<T> selectListAndCount(T entity, Integer pageNum, Integer pageSize, String orderBy) {
        String statementPostfix = entity.getClass().getName() + POSTFIX_SELECTLIST;
        return this.selectListAndCount(statementPostfix,entity,pageNum,pageSize,orderBy);
    }

    @Override
    public <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, Integer pageNum, Integer pageSize, String orderBy) {
        Page<?> page = PageHelper.startPage(pageNum, pageSize);

        if(!StringUtils.isEmpty(orderBy))
        {
            PageHelper.orderBy(orderBy);
        }
        List<E> list = getSqlSession().selectList(statementPostfix, entity);
        QueryResult<E> result = new QueryResult<>(list, page.getTotal());
        return result;
    }

    @Override
    public <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, Integer pageNum, Integer pageSize, String orderBy, String statementCount) {
        PageHelper.startPage(pageNum, pageSize, false);

        if(!StringUtils.isEmpty(orderBy))
        {
            PageHelper.orderBy(orderBy);
        }

        List<E> list = getSqlSession().selectList(statementPostfix, entity);
        long count = this.count(statementCount, entity);
        QueryResult<E> result = new QueryResult<>(list, count);

        return result;

    }
}
