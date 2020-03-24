package com.mickey.core.utils.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author J·K
 * @Description: 从request中获取参数值
 * @date 2020/3/24 6:20 下午
 */
public class ParamsUtils {

    /**
     * header/url/PathVariable 中获取参数
     * @param request
     * @param param
     * @return
     */
    public static String getParam(HttpServletRequest request,String param){
        String obj = request.getHeader(param);
        if(StringUtils.isBlank(obj)){
            obj = request.getParameter(param);
        }
        if(StringUtils.isBlank(obj)){
            Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            obj = (String)pathVariables.get(param);
        }
        return obj;
    }
}
