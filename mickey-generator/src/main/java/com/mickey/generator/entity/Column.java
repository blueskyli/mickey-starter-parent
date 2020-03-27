package com.mickey.generator.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Column
{
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String columnType;
    /**
     * 是否主键 仅支持单一主键
     */
    private boolean isPrimaryKey;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 字段长度
     */
    private int length;
    /**
     * 字段精度
     */
    private int precision;

    @Override
    public String toString()
    {
        return JSON.toJSONString(this);
    }
}
