package com.mickey.core.utils.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @Description: 服务调用追踪号工具类 会使用到MDC
 * @author J·K
 * @date 2018/5/7 15:52

 */
public class TraceIDUtils
{

    private static Logger log = LoggerFactory.getLogger(TraceIDUtils.class);

    private final static String ZERO_DOT_ZERO = "0.0";
    private final static String H_BAR = "-";//横杠
    private final static String TRACE_ID="traceId";

    /**
     * 创建TraceID为UUID+"0.0""
     */
    public static String createTraceID() {

        StringBuilder sb = new StringBuilder();

        String traceId = sb.append(UUIDUtils.randomUUID().toString()).append(H_BAR)
                .append(ZERO_DOT_ZERO).toString();
        MDC.put(TRACE_ID, traceId);
        log.debug("Create New TraceID is [{}]", traceId);
        return traceId;
    }
}
