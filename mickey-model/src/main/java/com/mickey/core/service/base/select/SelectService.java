package com.mickey.core.service.base.select;

import com.mickey.core.base.po.BasePo;

/**
 * @author J·K
 * @Description: SelectService
 * @date 2020/3/22 11:05 上午
 */
public interface SelectService<T extends BasePo>
        extends CountService<T>,SelectOneService<T>,SelectListService<T>{
}
