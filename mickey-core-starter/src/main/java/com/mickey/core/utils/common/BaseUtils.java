package com.mickey.core.utils.common;

/**
 * @Description: 基础工具方法
 * @author J·K
 * @date 2018/5/7 15:41

 */
public class BaseUtils
{

	/**
	 * 生成sql方法调用路径
	 * 
	 * @param <T>
	 *
	 * @param methodName 调用方法名称
	 * @return
	 */
	public static <T> String makeClazzPath(T object, String methodName) {

		if (object == null) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(object.getClass().getName());
		buffer.append(".");
		buffer.append(methodName);
		return buffer.toString();

	}

	public static <T> String makeClazzPath(Class<T> clazz, String methodName) {

		StringBuffer buffer = new StringBuffer();
		buffer.append(clazz.getName());
		buffer.append(".");
		buffer.append(methodName);
		return buffer.toString();

	}
}
