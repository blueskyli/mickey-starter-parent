package com.mickey.core.base.exception;

/**
 * @Description: 业务异常（消息应该是具体的错误描述）
 * @author J·K
 * @date 2018/5/7 16:02

 */
public class NoveServiceException extends AbstractException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3948591577017681256L;

	/**
	 * @param code
	 * @param message
	 */
	public NoveServiceException(String code, String message) {
		super(code, message);
	}

	public NoveServiceException(String code, String message, Throwable th) {
		super(code, message, th);
	}

	@Override
	public ErrorDesc getErrorDesc(Throwable var1) {
		return null;
	}

}
