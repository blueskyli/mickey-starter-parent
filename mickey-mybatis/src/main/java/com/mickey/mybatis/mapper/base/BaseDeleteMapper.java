package com.mickey.mybatis.mapper.base;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.dao.base.delete.DeleteDao;

/**
 * @author J·K
 * @Description: BaseDeleteMapper
 * @date 2020/3/22 9:55 上午
 */
public interface BaseDeleteMapper<T extends BasePo> extends DeleteDao<T> {

}
