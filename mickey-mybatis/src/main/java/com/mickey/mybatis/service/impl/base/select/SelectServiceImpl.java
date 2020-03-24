package com.mickey.mybatis.service.impl.base.select;

import com.mickey.model.functionalInterface.IDataSource;
import com.mickey.model.page.QueryResult;
import com.mickey.model.po.BasePo;
import com.mickey.mybatis.service.impl.base.insert.InsertServiceImpl;

import java.util.List;

/**
 * @author J·K
 * @Description: SelectServiceImpl
 * @date 2020/3/22 3:52 下午
 */
public abstract class SelectServiceImpl<T extends BasePo> extends InsertServiceImpl<T> {

    @Override
    public Integer count(T entity, IDataSource... args) {
        return super.getBaseDao(args).count(entity);
    }

    @Override
    public T selectOne(T entity, IDataSource... args) {
        return (T) super.getBaseDao(args).selectOne(entity);
    }

    @Override
    public List<T> selectList(T entity, IDataSource... args) {
        return super.getBaseDao(args).selectList(entity);
    }

    @Override
    public List<T> selectList(T entity, String orderBy, IDataSource... args) {
        return super.getBaseDao(args).selectList(entity, orderBy);
    }

    @Override
    public List<T> selectList(T entity, int pageNum, int pageSize, IDataSource... args) {
        return super.getBaseDao(args).selectList(entity, pageNum, pageSize);
    }

    @Override
    public List<T> selectList(T entity, int pageNum, int pageSize, String orderBy, IDataSource... args) {
        return super.getBaseDao(args).selectList(entity, pageNum, pageSize, orderBy);
    }

    @Override
    public <E> Integer count(String statementPostfix, E entity, IDataSource... args) {
        return super.getBaseDao(args).count(statementPostfix, entity);
    }

    @Override
    public <E> E selectOne(String statementPostfix, E entity, IDataSource... args) {
        return (E) super.getBaseDao(args).selectOne(statementPostfix, entity);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, IDataSource... args) {
        return super.getBaseDao(args).selectList(statementPostfix, entity);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, String orderBy, IDataSource... args) {
        return super.getBaseDao(args).selectList(statementPostfix, entity, orderBy);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, int pageNum, int pageSize, IDataSource... args) {
        return super.getBaseDao(args).selectList(statementPostfix, entity, pageNum, pageSize);
    }

    @Override
    public <E> List<E> selectList(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, IDataSource... args) {
        return super.getBaseDao(args).selectList(statementPostfix, entity, pageNum, pageSize, orderBy);
    }

    @Override
    public QueryResult<T> selectListAndCount(T entity, Integer pageNum, Integer pageSize, String orderBy, IDataSource... args) {
        return super.getBaseDao(args).selectListAndCount(entity, pageNum, pageSize, orderBy);
    }

    @Override
    public <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, IDataSource... args) {
        return super.getBaseDao(args).selectListAndCount(statementPostfix, entity, pageNum, pageSize, orderBy);
    }

    @Override
    public <E> QueryResult<E> selectListAndCount(String statementPostfix, E entity, int pageNum, int pageSize, String orderBy, String statementCount, IDataSource... args) {
        return super.getBaseDao(args).selectListAndCount(statementPostfix, entity, pageNum, pageSize, orderBy, statementCount);
    }
}
