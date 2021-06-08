package com.mickey.generator.utils;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.mickey.core.exception.NoveSystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author J·K
 * @Description: 类型转换工具类
 * @date 2020/3/25 5:27 下午
 */
@Slf4j
public class ConvertUtils {

    /**
     * jdbcType to javaType to packagename
     */
    private static Table<String, String, String> jdbc2javaTypeTable;

    static {
        jdbc2javaTypeTable = ImmutableTable.<String, String, String>builder()
            .put("bigint", "Long", "")
            .put("datetime", "Date", "java.util.Date")
            .put("date", "Date", "java.util.Date")
            .put("timestamp", "Date", "java.util.Date")
            .put("time", "Date", "java.util.Date")
            .put("varchar", "String", "")
            .put("text", "String", "")
            .put("char", "String", "")
            .put("decimal", "BigDecimal", "java.math.BigDecimal")
            .put("int", "Integer", "")
            .put("tinyint", "Integer", "")
            .put("smallint", "Integer", "")
            .put("bit", "Boolean", "")
            .put("boolean", "Boolean", "")
            .put("float", "Float", "")
            .put("double", "Double", "")
            .build();
    }

    /**
     * 下划线对应的ASCII
     */
    private static final byte ASCII_UNDER_LINE = 95;

    /**
     * 小写字母a的ASCII
     */
    private static final byte ASCII_a = 97;

    /**
     * 大写字母A的ASCII
     */
    private static final byte ASCII_A = 65;

    /**
     * 小写字母z的ASCII
     */
    private static final byte ASCII_z = 122;

    /**
     * 字母a和A的ASCII差距(a-A的值)
     */
    private static final byte ASCII_a_A = ASCII_a - ASCII_A;

    /**
     * 将参数b转换为大写字母,小写字母ASCII范围(97~122)
     * 0. 判断参数是否为小写字母
     * 1. 将小写字母转换为大写字母(减去32)
     */
    private static byte toUpper(byte b) {
        if (b >= ASCII_a && b <= ASCII_z) {
            return (byte) (b - ASCII_a_A);
        }
        return b;
    }

    /**
     * 交换下划线和其后面字符的下标
     * 将column从下划线命名方式转换成驼峰命名方式
     * 0. 找到`_`符号的ASCII码(95)对应的下标
     * 1. 将下划线的下标的下一个元素转换为大写字段(如果是小写字母的话)并放到下划线对应的下标
     * 2. 将下划线下标的下一个元素设置为下划线
     * 3. 返回数组
     *
     * @param column 字段名称
     */
    private static byte[] changeIdx(String column) {
        byte[] bytes = column.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == ASCII_UNDER_LINE) {
                if (i < bytes.length - 1) {
                    bytes[i] = toUpper(bytes[i + 1]);
                    bytes[i + 1] = ASCII_UNDER_LINE;
                    i++;
                }
            }
        }
        return bytes;
    }

    /**
     * 去除所有下划线
     * 0. 新创建一个数组
     * 1. 将所有非下划线字符都放入新数组中
     *
     * @param bytes 原始数组
     * @return 处理后的字节数组
     */
    private static byte[] removeUnderLine(byte[] bytes) {
        // 存放非下划线字符的数量
        int count = 0;
        for (byte b : bytes) {
            if (b == ASCII_UNDER_LINE) {
                continue;
            }
            count++;
        }
        byte[] nBytes = new byte[count];
        count = 0;
        for (byte b : bytes) {
            if (b == ASCII_UNDER_LINE) {
                continue;
            }
            nBytes[count] = b;
            count++;
        }
        return nBytes;
    }

    /**
     * 将数据库名称转换为实体名
     *
     * @param tableName
     * @return
     */
    public static String convertTableName2EntityName(String tableName) {
        return upperFirst(convertColumnName2PropName(tableName));
    }

    /**
     * 将数据库字段名，转为属性名
     * 将下划线命名转换驼峰式命名
     * 0. 转换成大写,交换下标
     * 1. 去除所有下划线
     *
     * @param columnName
     * @return
     */
    public static String convertColumnName2PropName(String columnName) {
        byte[] bytes = changeIdx(columnName);
        bytes = removeUnderLine(bytes);
        return new String(bytes);
    }

    /**
     * 将字符串的首字母转换为大写
     *
     * @param str
     * @return
     */
    public static String upperFirst(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        if (1 == str.length()) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将字符串的首字母转换为小写.
     *
     * @param str
     * @return
     */
    public static String lowerFirst(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        if (1 == str.length()) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 根据jdbcType得到javaType 和 package
     * @param rowKey columeType
     * @return
     */
    public static Map<String, String> javaTypeMap(String rowKey) {
        Map<String, String> map = jdbc2javaTypeTable.row(rowKey);
        if (map.size() != 1) {
            throw new NoveSystemException("500",
                String.format("%s对应的javaType类型重复或未配置", rowKey));
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(convertColumnName2PropName("qr_code_id"));
        System.out.println(convertTableName2EntityName("sc_qr_Code"));
        Map<String, String> row = jdbc2javaTypeTable.row("bigint");
        if (row.size() == 1) {
            row.forEach((k, v) -> {
                log.info("key:{}", k);
                log.info("value:{}", v);
            });
        } else {
            System.out.println("匹配项重复或未配置");
        }
    }
}
