package com.mickey.core.base.annotation;

/**
 * 
 * @Description: 接收数据源，返回值为String
 * @author Jack
 * @date 2017年11月1日 下午5:21:16
 *
 */
@FunctionalInterface
public interface IDataSource
{
    String getBaseDao();
}
