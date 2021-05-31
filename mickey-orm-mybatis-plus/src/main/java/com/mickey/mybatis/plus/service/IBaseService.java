package com.mickey.mybatis.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mickey.model.po.BasePo;

/**
 * @author J·K
 * @description: IBaseService
 * @date 2021/5/31 10:58 上午
 */
public interface IBaseService<T extends BasePo> extends IService<T> {
}
