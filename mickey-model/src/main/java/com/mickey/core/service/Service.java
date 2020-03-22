package com.mickey.core.service;

import com.mickey.core.base.po.BasePo;
import com.mickey.core.service.base.delete.DeleteService;
import com.mickey.core.service.base.insert.InsertService;
import com.mickey.core.service.base.select.SelectService;
import com.mickey.core.service.base.update.UpdateService;

/**
 * @author J·K
 * @Description: 提供此类给xml的namespace为mapper路径使用，新版本
 * @date 2020/3/22 11:26 上午
 */
public interface Service<T extends BasePo>
        extends InsertService<T>, DeleteService<T>, UpdateService<T>, SelectService<T> {
}
