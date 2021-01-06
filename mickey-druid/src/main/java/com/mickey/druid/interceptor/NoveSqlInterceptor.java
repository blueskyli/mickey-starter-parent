package com.mickey.druid.interceptor;

import com.google.common.collect.Maps;
import com.mickey.druid.utils.EscapeUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author J·K
 * @Description: 过滤模糊查询特殊字符（_、%、\）
 * @date 2019/12/12 9:38 下午
 */
@Slf4j
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class NoveSqlInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 拦截sql
        Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        Object parameterObject = args[1];
        BoundSql boundSql = statement.getBoundSql(parameterObject);
        String sql = boundSql.getSql();
        // 处理特殊字符
        Map<String, String> map = Maps.newHashMap();
        modifyLikeSql(sql, parameterObject, boundSql, map);
        // 返回
        Object proceed = invocation.proceed();

        if (map.size() > 0) {
            log.info("撤销的数据：{}", map);
            MetaObject metaObject = new Configuration().newMetaObject(parameterObject);
            map.forEach((k, v) -> metaObject.setValue(k, v));
        }
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @SuppressWarnings("unchecked")
    private String modifyLikeSql(String sql, Object parameterObject, BoundSql boundSql, Map<String, String> map) {

        if (!sql.toLowerCase().contains(" like ") || !sql.toLowerCase().contains("?")) {
            return sql;
        }
        // 获取关键字的个数（去重）
        String[] strList = sql.split("\\?");
        Set<String> keyNames = new HashSet<>();
        for (int i = 0; i < strList.length; i++) {
            if (EscapeUtils.allTrim(strList[i].toLowerCase()).contains(EscapeUtils.allTrim(" like concat("))) {
                String keyName = boundSql.getParameterMappings().get(i).getProperty();
                keyNames.add(keyName);
            }
        }
        // 对关键字进行特殊字符“清洗”，如果有特殊字符的，在特殊字符前添加转义字符（\）
        for (String keyName : keyNames) {
            log.info("[NoveSqlInterceptor]->{}", "ONE");
            // 第一种情况：在Mapper类的注解SQL中进行了模糊查询的拼接
            MetaObject metaObject = new Configuration().newMetaObject(parameterObject);
            Object a = metaObject.getValue(keyName);
            if (a instanceof String && (a.toString().contains("_") || a.toString().contains("\\") || a.toString().contains("%"))) {
                map.put(keyName, a.toString());
                metaObject.setValue(keyName, EscapeUtils.escapeChar(a.toString()));
            }
        }
        log.info("[NoveSqlInterceptor]->parameterObject:{}", parameterObject);
        return sql;
    }
}