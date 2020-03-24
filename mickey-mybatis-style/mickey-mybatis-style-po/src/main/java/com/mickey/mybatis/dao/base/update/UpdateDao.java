package com.mickey.mybatis.dao.base.update;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.base.update.UpdateMapper;

import java.util.List;

/**
 * @author J·K
 * @Description: UpdateDao
 * @date 2020/3/22 4:26 下午
 */
public interface UpdateDao<T extends BasePo> extends UpdateMapper<T> {
}
