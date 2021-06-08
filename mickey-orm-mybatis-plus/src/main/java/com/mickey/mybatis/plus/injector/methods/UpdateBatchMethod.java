package com.mickey.mybatis.plus.injector.methods;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.mickey.mybatis.plus.enums.SqlMethod;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.function.Predicate;

/**
 * @author J·K
 * @description: 根据ID批量修改数据
 * @date 2021/6/4 10:05 上午
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBatchMethod extends AbstractMethod {

    @Setter
    @Accessors(chain = true)
    private Predicate<TableFieldInfo> predicate;

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.UPDATE_LIST_BY_ID;

        final String additional = tableInfo.isWithVersion() ? tableInfo.getVersionFieldInfo().getVersionOli("item", "item.") : "" + tableInfo.getLogicDeleteSql(true, true);
        String sqlSet = this.filterTableFieldInfo(tableInfo.getFieldList(), getPredicate(),
            i -> i.getSqlSet(false, "item."), NEWLINE);
        sqlSet = SqlScriptUtils.convertSet(sqlSet);
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlSet,
            tableInfo.getKeyColumn(), "item." + tableInfo.getKeyProperty(), additional);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);
    }

    private Predicate<TableFieldInfo> getPredicate() {
//        Predicate<TableFieldInfo> noLogic = t -> !t.isLogicDelete();
        Predicate<TableFieldInfo> noLogic = x -> true;
        if (predicate != null) {
            return noLogic.and(predicate);
        }
        return noLogic;
    }
}
