package com.mickey.core.utils.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author J·K
 * @Description: EscapeUtils
 * @date 2019/12/12 9:43 下午
 */
public class EscapeUtils {
    //mysql的模糊查询时特殊字符转义
    public static String escapeChar(String before){
        if(StringUtils.isNotBlank(before)){
            before = before.replaceAll("\\\\", "\\\\\\\\");
            before = before.replaceAll("_", "\\\\_");
            before = before.replaceAll("%", "\\\\%");
        }
        return before ;
    }

    /**
     * 去掉字符串中空格、制表符、换页符等空白字符
     * @param origin
     * @return
     */
    public static String allTrim(String origin){
        return origin.replaceAll("\\s*", "");
    }

    /**
     * 去除字符串中的空格
     * @param origin
     * @return
     */
    public static String trim(String origin){
        return origin.replaceAll(" +", "");
    }

    public static void main(String[] args) {
        String str = " a d g d  (&*  \n    dddg  d ";
        System.out.println(trim(str));
        System.out.println(allTrim(str));
    }
}
