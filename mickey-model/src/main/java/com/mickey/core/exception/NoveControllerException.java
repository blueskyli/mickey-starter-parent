package com.mickey.core.exception;

/**
 * @Description: 控制层异常（消息应该是具体的错误描述）
 * @author J·K
 * @date 2018/5/7 16:00

 */
public class NoveControllerException extends AbstractException {

	private static final long serialVersionUID = 8307533385237791476L;

	/**
	 * @param code
	 * @param message
	 */
	public NoveControllerException(String code, String message) {
		super(code, message);
	}

    public NoveControllerException(String message) {
        super(String.valueOf(500), message);
    }

    public NoveControllerException(Integer code, String message) {
        super(String.valueOf(code), message);
    }

	public NoveControllerException(String code, String message, Throwable th) {
		super(code, message, th);
	}

}
