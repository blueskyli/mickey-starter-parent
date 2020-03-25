package com.mickey.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author J·K
 * @Description: ErrorCodeEnum
 * @date 2018/7/31 10:55
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    OK(200, "成功"),
    NO_TOKEN(310, "帐号未登录"),
    TOKEN_TIME_OUT(311, "登录超时"),
    UNKNOWN(-1, "未知错误"),
    INTERNAL_ERROR(500, "系统错误，请联系管理员"),
    PARAM_NOT_SIGNATURE(801, "请求参数未签名"),
    SIGNATURE_GET_ERROR(805, "签名获取失败"),
    PARAM_SIGNATURE_INCORRECT(802, "参数签名值不正确"),
    PARAM_IS_ERROR(1004, "请求参数错误"),
    PARAM_FORMAT_ERROR(1008, "参数格式错误"),

    ;

    private Integer code;
    private String desc;

    public String getStrCode() {
        return code.toString();
    }

}
