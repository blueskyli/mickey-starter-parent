package com.mickey.mybatis.mapper;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.mapper.base.BaseDeleteMapper;
import com.mickey.mybatis.mapper.base.BaseInsertMapper;
import com.mickey.mybatis.mapper.base.BaseSelectMapper;
import com.mickey.mybatis.mapper.base.BaseUpdateMapper;

/**
 * @author J·K
 * @Description: BaseMapper
 * @date 2020/3/20 6:27 下午
 */
public interface BaseMapper<T extends BasePo>
    extends
    BaseDeleteMapper<T>,
    BaseInsertMapper<T>,
    BaseSelectMapper<T>,
    BaseUpdateMapper<T> {

}
