package com.mickey.mybatis.plus.config;

import lombok.Data;

/**
 * @author J·K
 * @description: 删除字段配置
 * @date 2021/5/28 1:46 下午
 */
public interface LogicDeleteConfig {
    /**
     * 逻辑删除字段名
     */
    String delColumnName = "del_flag";
    /**
     * 逻辑删除实体属性名
     */
    String delFiledName = "delFlag";
    /**
     * 删除
     */
    String delValue = "1";
    /**
     * 未删除
     */
    String notDelValue = "0";
}
