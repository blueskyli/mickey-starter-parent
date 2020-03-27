package com.mickey.generator.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author J·K
 * @Description: 组装xml文件实体类
 * @date 2020/3/26 2:50 下午
 */
@Data
@Accessors(chain=true)
public class XmlClass {

    /**
     * xml nagespace
     */
    private String xmlNameSpace;
    /**
     * 实体类全路径
     */
    private String entityPath;
    /**
     * table名称
     */
    private String tableName;
    /**
     * 主键列名称
     */
    private String PkName;
    /**
     * 列 -> 字段对应关系
     */
    private List<EntityClass.Field> fields;

}
