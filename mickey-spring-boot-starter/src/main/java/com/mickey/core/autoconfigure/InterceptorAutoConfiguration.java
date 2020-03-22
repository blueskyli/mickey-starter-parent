package com.mickey.core.autoconfigure;

import com.mickey.core.config.AbstractConfigurerAdapter;
import com.mickey.core.autoconfigure.interceptor.NoveControllerInterceptor;
import com.mickey.core.utils.common.PropertyConfigUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * 内置拦截器
 */
@Slf4j
@Configuration
public class InterceptorAutoConfiguration extends AbstractConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(noveControllerInterceptor()).addPathPatterns("/**");
		log.info("添加追踪ID生成拦截器 ,拦截规则:[{}]", "/**");
	}

	@Bean
    public NoveControllerInterceptor noveControllerInterceptor(){
	    return new NoveControllerInterceptor();
    }

	@Bean
	public PropertyConfigUtils propertyConfigUtils() {
		return new PropertyConfigUtils();
	}
}
