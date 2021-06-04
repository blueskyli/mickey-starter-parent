package com.mickey.mybatis.plus.injector.methods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.mickey.mybatis.plus.enums.SqlMethod;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author J·K
 * @description: (重写原有update方法)根据 ID 更新有值字段
 * @date 2021/6/4 10:04 上午
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMethod extends AbstractMethod {

    @Setter
    @Accessors(chain = true)
    private Predicate<TableFieldInfo> predicate;

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        com.mickey.mybatis.plus.enums.SqlMethod sqlMethod = SqlMethod.UPDATE_BY_ID;
        final String additional = optlockVersion(tableInfo) + tableInfo.getLogicDeleteSql(true, true);
        String sqlSet = this.filterTableFieldInfo(tableInfo.getFieldList(), getPredicate(),
            i -> i.getSqlSet(false, ENTITY_DOT), NEWLINE);
        sqlSet = SqlScriptUtils.convertSet(sqlSet);
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlSet,
            tableInfo.getKeyColumn(), ENTITY_DOT + tableInfo.getKeyProperty(), additional);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, SqlMethod.UPDATE.getMethod(), sqlSource);
    }

    private Predicate<TableFieldInfo> getPredicate() {
        Predicate<TableFieldInfo> noLogic = x -> true;
        if (predicate != null) {
            return noLogic.and(predicate);
        }
        return noLogic;
    }
}
