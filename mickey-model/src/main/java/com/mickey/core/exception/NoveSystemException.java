package com.mickey.core.exception;

/**
 * @Description: 系统异常,一般为非NoveServiceException异常
 * @author J·K
 * @date 2018/6/21 14:31

 */
public class NoveSystemException extends AbstractException {

    private static final long serialVersionUID = -708576626654664864L;

    /**
     * @param code
     * @param message
     */
    public NoveSystemException(String code, String message) {
        super(code, message);
    }

    public NoveSystemException(Integer code, String message) {
        super(String.valueOf(code), message);
    }

    public NoveSystemException(String code, String message, Throwable th) {
        super(code, message, th);
    }

}
