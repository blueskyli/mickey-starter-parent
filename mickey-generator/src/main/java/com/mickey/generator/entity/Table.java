package com.mickey.generator.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain=true)
public class Table
{
    /**
     * 名称
     */
    private String tableName;
    /**
     * 类型
     */
    private String type;
    /**
     * 备注
     */
    private String remark;
    /**
     * 主键列名称
     */
    private String pkName;
    /**
     * 列集合
     */
    private List<Column> columns;

    @Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
}
