package com.mickey.core.autoconfigure.interceptor;

import com.mickey.core.utils.lang.TraceIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author J·K
 * @Description: 为了在Http请求入口生成TRACE_ID, 日志ID
 * @date 2018/5/7 15:45
 */
@Slf4j
public class NoveControllerInterceptor extends HandlerInterceptorAdapter
{
    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        try
        {
            TraceIDUtils.createTraceID();
        }
        catch(Exception e)
        {//容灾
            log.error("HTTP入口追踪ID 产生过程出现错误", e);
        }
        return true;
    }

    @Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
    }

    @Override public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        /* 线程结束后需要清除,否则当前线程会一直占用这个TRACE_ID值 */
        log.debug("method is end , clear MDC !!!");
        MDC.clear();
    }
}
