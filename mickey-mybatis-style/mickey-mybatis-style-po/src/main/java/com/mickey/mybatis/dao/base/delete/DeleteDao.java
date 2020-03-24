package com.mickey.mybatis.dao.base.delete;

import com.mickey.model.po.BasePo;
import com.mickey.mybatis.base.delete.DeleteMapper;

/**
 * @author J·K
 * @Description: DeleteDao
 * @date 2020/3/22 4:23 下午
 */
public interface DeleteDao<T extends BasePo> extends DeleteMapper<T> {
}
