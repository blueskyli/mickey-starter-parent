package com.mickey.core.utils.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拼音工具类
 *
 * @author kiwiam
 *
 */
public abstract class PinyinUtil {

    private static final Logger   log   = LoggerFactory.getLogger(PinyinUtil.class);

    protected static final String EMPTY = "";

    /**
     * 转换为汉字拼音<br>
     * 非中文字符不进行转换
     *
     * @param str
     * @return
     */
    public static String toPinyin(String str) {

        if (StringUtils.isBlank(str)) {
            return EMPTY;
        }

        char[] charArr = str.toCharArray();

        HanyuPinyinOutputFormat format = getPinyinOutputFormat();
        StringBuilder strBuf = new StringBuilder();
        try {
            for (int i = 0; i < charArr.length; i++) {
                // 判断是否为汉字字符
                if (Character.toString(charArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    strBuf.append(PinyinHelper.toHanyuPinyinStringArray(charArr[i], format)[0]);
                } else {
                    strBuf.append(charArr[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("拼音工具类toPinyin异常", e);
        }

        return strBuf.toString();
    }

    /**
     * 转换为汉字简拼<br>
     * 非中文字符不进行转换
     *
     * @param str
     * @return
     */
    public static String toSimplifiedPinyin(String str) {

        if (StringUtils.isBlank(str)) {
            return EMPTY;
        }

        char[] charArr = str.toCharArray();

        HanyuPinyinOutputFormat format = getPinyinOutputFormat();
        StringBuilder strBuf = new StringBuilder();
        try {
            for (int i = 0; i < charArr.length; i++) {
                // 判断是否为汉字字符
                if (Character.toString(charArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String pinyin = PinyinHelper.toHanyuPinyinStringArray(charArr[i], format)[0];
                    if (StringUtils.isNotBlank(pinyin) && pinyin.length() > 0) {
                        strBuf.append(pinyin.charAt(0));
                    }
                } else {
                    strBuf.append(charArr[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            log.error("拼音工具类toSimplifiedPinyin异常", e);
        }

        return strBuf.toString();
    }

    /**
     * 转换拼音格式
     *
     * @return
     */
    private static HanyuPinyinOutputFormat getPinyinOutputFormat() {

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        // 设置小写
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 设置不包含音调
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 拼音ü使用 v表示
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        return format;
    }

}
