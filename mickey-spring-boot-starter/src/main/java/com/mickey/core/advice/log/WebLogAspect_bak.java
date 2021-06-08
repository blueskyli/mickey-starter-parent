package com.mickey.core.advice.log;

import com.google.common.collect.Sets;
import com.mickey.core.utils.common.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author J·K
 * @Description: web层日志切面
 * @date 2018/7/27 18:06
 */
//@Aspect
//@Component
@Order(1)
@Slf4j
public class WebLogAspect_bak {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private static final String POINT_CUT = "execution(public * com.ecej.member.server.controller..*.*(..))";
    private static final Set<String> METHOD_SET = Sets.newHashSet("POST", "PUT", "PATCH", "DELETE");

    @Pointcut(POINT_CUT)
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        printRequestMsg(request, joinPoint);
    }

    private void printRequestMsg(HttpServletRequest request, JoinPoint joinPoint) {
        String method = request.getMethod();
        log.info("【full requestURL】:" + request.getRequestURL().toString());
        log.info("【ip】:" + IpUtils.getIpAddr(request));
        log.info("【remoteAddr】:" + request.getRemoteAddr());
        log.info("【remoteHost】:" + request.getRemoteHost());
        log.info("【localAddr】:" + request.getLocalAddr());
        log.info("【requestMethod】:" + method);
        log.info("【headers】:" + getHeadersInfo(request));
        log.info("【parameters】:" + this.getParam(request.getParameterMap()));
        if (METHOD_SET.contains(method)) {
            log.info("【" + request.getMethod() + "Params】:" + getReqestParam(joinPoint, request));
        }
        log.info("【classMethod】:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    private Map<String, Object> getReqestParam(JoinPoint joinPoint, HttpServletRequest request) {
        Map<String, Object> paramMap = new LinkedHashMap<>();
        //获取连接点（Joint Point）的签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < parameterNames.length; i++) {
            if (args[i] instanceof HttpServletRequest) {
                Enumeration<String> params = request.getParameterNames();
                while (params.hasMoreElements()) {
                    String key = params.nextElement();
                    paramMap.put(key, request.getParameter(key));
                }
            } else if (!(args[i] instanceof HttpServletResponse)) {
                paramMap.put(parameterNames[i], args[i]);
            }
        }
        return paramMap;
    }

    private String getParam(Map<String, String[]> map) {
        StringBuilder str = new StringBuilder();
        for (String key : map.keySet()) {
            if (!str.toString().equals(""))
                str.append("&");
            str.append(key + "= " + String.join(",", map.get(key)));
        }
        return str.toString();
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    @AfterReturning(returning = "response", pointcut = "webLog()")
    public void doAfterReturning(Object response) {
        // 处理完请求，返回内容
        log.info("【RESPONSE】: " + response);
        log.info("【SPEND TIME】: " + (System.currentTimeMillis() - startTime.get()) + "ms");
    }
}
