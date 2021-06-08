package com.mickey.core.exception;

/**
 * @author J·K
 * @Description: 业务异常（消息应该是具体的错误描述）
 * @date 2018/5/7 16:02
 */
public class NoveServiceException extends AbstractException {

    private static final long serialVersionUID = -3948591577017681256L;

    /**
     * @param code
     * @param message
     */
    public NoveServiceException(String code, String message) {
        super(code, message);
    }

    public NoveServiceException(String message) {
        super(String.valueOf(500), message);
    }

    public NoveServiceException(Integer code, String message) {
        super(String.valueOf(code), message);
    }

    public NoveServiceException(String code, String message, Throwable th) {
        super(code, message, th);
    }
}
