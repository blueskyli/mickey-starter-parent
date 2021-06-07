package com.mickey.core.utils.common;

import lombok.experimental.UtilityClass;
import org.slf4j.MDC;

/**
 * @author J·K
 * @description: MDCUtils
 * @date 2021/6/7 12:45 下午
 */
@UtilityClass
public class MDCUtils {

    /**
     * X-B3-TraceId
     *
     * @return
     */
    public String getB3TraceId() {
        return MDC.get("X-B3-TraceId");
    }

    /**
     * X-B3-SpanId
     *
     * @return
     */
    public String getB3SpanId() {
        return MDC.get("X-B3-SpanId");
    }

    /**
     * TraceID
     *
     * @return
     */
    public String getTraceId() {
        return MDC.get("traceId");
    }
}
