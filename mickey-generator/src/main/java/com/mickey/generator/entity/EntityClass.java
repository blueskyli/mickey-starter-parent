package com.mickey.generator.entity;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

/**
 * @author J·K
 * @Description: Po原型类
 * @date 2020/3/25 6:39 下午
 */
@Data
@Accessors(chain=true)
public class EntityClass {
    /**
     * 实体类名称（不带后缀）
     */
    private String tableAlias;
    /**
     * 实体类名称（带后缀）
     */
    private String className;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 基础包路径
     */
    private String basePackage;
    /**
     * entity包全路径
     */
    private String allPackage;
    /**
     * 需要导入的包
     */
    private Set<String> imports = Sets.newHashSet();
    /**
     * 主键名称(属性名)
     */
    private String pkName;
    /**
     * 字段信息
     */
    private List<Field> fields;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Field{
        /**
         * 数据库列名称
         */
        private String columnName;
        /**
         * 是否主键
         */
        private boolean isPrimaryKey;
        /**
         * 字段名
         */
        private String fieldName;
        /**
         * 字段类型
         */
        private String fieldType;
        /**
         * 字段备注
         */
        private String fieldRemark;
    }
}
