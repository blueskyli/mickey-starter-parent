package com.mickey.mybatis.dao.base.insert;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.base.insert.InsertMapper;

import java.util.List;

/**
 * @author J·K
 * @Description: InsertDao
 * @date 2020/3/22 4:25 下午
 */
public interface InsertDao<T extends BasePo> extends InsertMapper<T> {
}
