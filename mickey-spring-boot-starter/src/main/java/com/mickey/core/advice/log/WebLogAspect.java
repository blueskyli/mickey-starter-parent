package com.mickey.core.advice.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import com.mickey.core.utils.common.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * @author J·K
 * @description: 切面
 * @date 2021/5/26 2:57 下午
 */
@Slf4j
public class WebLogAspect implements MethodInterceptor {

    ThreadLocal<Long> startTime = new ThreadLocal<>();
    private static final Set<String> METHOD_SET = Sets.newHashSet("POST", "PUT", "PATCH", "DELETE");

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //这里做你的before操作
        doBefore(invocation);

        Object result = invocation.proceed();
        //这里做你的after操作
        doAfter(result);
        return result;
    }

    private void doBefore(MethodInvocation invocation) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        printRequestMsg(request, invocation);
    }

    private void printRequestMsg(HttpServletRequest request, MethodInvocation invocation) {
        log.info("【full requestURL】:" + request.getRequestURL().toString());
        log.info("【ip】:" + IpUtils.getIpAddr(request));
        log.info("【remoteAddr】:" + request.getRemoteAddr());
        log.info("【remoteHost】:" + request.getRemoteHost());
        log.info("【localAddr】:" + request.getLocalAddr());
        log.info("【requestMethod】:" + request.getMethod());
        log.info("【headers】:" + getHeadersInfo(request));
        log.info("【parameters】:" + this.getParam(request.getParameterMap()));

        Method method = invocation.getMethod();
        if (METHOD_SET.contains(request.getMethod())) {
            log.info("【" + request.getMethod() + "Params】:" + getReqestParam(invocation));
        }
        log.info("【classMethod】:" + method.getDeclaringClass().getName() + "." + method.getName());
    }

    private Map<String, Object> getReqestParam(MethodInvocation invocation) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> paramMap = new LinkedHashMap<>();
            Object[] args = invocation.getArguments();
            Method method = invocation.getMethod();
            Parameter[] parameters = method.getParameters();
            if (ArrayUtils.isEmpty(parameters)) {
                return null;
            }
            for (int i = 0; i < parameters.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) args[i];
                    Enumeration<String> params = request.getParameterNames();
                    while (params.hasMoreElements()) {
                        String key = params.nextElement();
                        paramMap.put(key, request.getParameter(key));
                    }
                } else if (!(args[i] instanceof HttpServletResponse)) {
                    paramMap.put(parameters[i].getName(), objectMapper.writeValueAsString(args[i]));
                }
            }
            return paramMap;
        } catch (JsonProcessingException e) {
            return null;
        }
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

    private String getParam(Map<String, String[]> map) {
        StringBuilder str = new StringBuilder();
        for (String key : map.keySet()) {
            if (!str.toString().equals(""))
                str.append("&");
            str.append(key + "= " + String.join(",", map.get(key)));
        }
        return str.toString();
    }

    private void doAfter(Object response) {
        // 处理完请求，返回内容
        log.info("【RESPONSE】: " + response);
        log.info("【SPEND TIME】: " + (System.currentTimeMillis() - startTime.get()) + "ms");
    }
}
