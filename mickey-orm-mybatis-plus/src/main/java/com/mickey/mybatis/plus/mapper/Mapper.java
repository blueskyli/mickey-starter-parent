package com.mickey.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mickey.model.po.BasePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author J·K
 * @description: Mapper
 * @date 2021/5/31 11:09 上午
 */
public interface Mapper<T extends BasePo> extends BaseMapper<T> {

    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer insertList(@Param("list") List<T> list);

    /**
     * 根据主键批量更新
     * @param list
     * @return
     */
    Integer updateList(@Param("list") List<T> list);
}
