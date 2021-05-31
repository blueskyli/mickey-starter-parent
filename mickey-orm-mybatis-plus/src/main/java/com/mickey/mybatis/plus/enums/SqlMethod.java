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
     * 插入
     */
    INSERT("insert", "插入一条数据（选择字段插入）", "<script>\nINSERT INTO %s %s VALUES %s\n</script>"),
    INSERT_LIST("insertList", "批量插入数据", "<script>\nINSERT INTO %s %s VALUES %s\n</script>"),
    /**
     * 修改
     */
    UPDATE("update", "根据ID 选择修改数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
    UPDATE_LIST("updateList", "根据ID 批量修改数据", "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>"),
    /**
     * 删除
     */
    DELETE("delete", "根据ID 删除一条数据", "<script>\nDELETE FROM %s WHERE %s=#{%s}\n</script>"),
    DELETE_LIST("deleteList", "根据ID 批量删除数据", "<script>\nDELETE FROM %s WHERE %s in(#{%s})\n</script>"),
    /**
     * 查询
     */
    COUNT("count", "查询满足条件总记录数", "<script>%s SELECT COUNT(%s) FROM %s %s %s\n</script>"),
    SELECT_ONE("selectOne", "查询满足条件一条数据", "<script>%s SELECT %s FROM %s %s %s\n</script>"),
    SELECT_LIST("selectList", "查询满足条件所有数据", "<script>%s SELECT %s FROM %s %s %s %s\n</script>"),
    SELECT_LIST_BY_IDS("selectListByIds", "根据ID集合，批量查询数据", "<script>SELECT %s FROM %s WHERE %s IN (%s) %s </script>"),
    ;

    private String method;
    private String desc;
    private String sql;

}
