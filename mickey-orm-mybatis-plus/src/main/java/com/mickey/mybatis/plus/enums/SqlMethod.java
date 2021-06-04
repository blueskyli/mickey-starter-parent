package com.mickey.mybatis.plus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author J·K
 * @description: MybatisPlus 扩展 SQL 方法
 * @date 2021/5/28 6:22 下午
 */
@Getter
@AllArgsConstructor
public enum SqlMethod {

    /**
     * 批量插入
     */
    INSERT_LIST("insertList", "批量插入数据", "<script>\nINSERT INTO %s %s VALUES %s\n</script>"),
    INSERT_ONE("insert", "插入一条数据（选择字段插入）", "<script>\nINSERT INTO %s %s VALUES %s\n</script>"),

    /**
     * 批量修改
     */
    UPDATE_LIST_BY_ID("updateList", "根据ID批量修改数据", "<script>\n<foreach collection=\"list\" item=\"item\" separator=\";\">\nupdate %s %s where %s=#{%s} %s\n</foreach>\n</script>"),
    UPDATE_BY_ID("updateById", "根据ID 选择修改数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
    UPDATE("update", "根据 whereEntity 条件，更新记录", "<script>\nUPDATE %s %s %s %s\n</script>"),
    ;

    private String method;
    private String desc;
    private String sql;

}
