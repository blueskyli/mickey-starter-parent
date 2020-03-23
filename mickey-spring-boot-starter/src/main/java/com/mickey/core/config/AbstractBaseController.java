package com.mickey.core.config;

import com.mickey.core.utils.common.DateEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author J·K
 * @Description: 抽象父类
 * @date 2020/3/23 7:33 下午
 */
public abstract class AbstractBaseController {
    @Resource
    public HttpServletRequest request;

    @Resource
    public HttpServletResponse response;

    @Autowired
    public ApplicationContext applicationContext;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 注入日期转换器
        binder.registerCustomEditor(Date.class, new DateEditor());
    }
}
