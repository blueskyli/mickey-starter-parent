package com.mickey.core.utils.common;

/**
 * @author J·K
 * @Description: 获取需要的年月日时分秒
 * @date 2020/12/29 12:04 下午
 */
@FunctionalInterface
public interface DateFunction<Y, MM, D, H, M, S, R> {
    /**
     * 返回需要的信息
     * @param y  年
     * @param mm 月
     * @param d  日
     * @param h  时
     * @param m  分
     * @param s  秒
     * @return
     */
    R apply(Y y, MM mm, D d, H h, M m, S s);
}