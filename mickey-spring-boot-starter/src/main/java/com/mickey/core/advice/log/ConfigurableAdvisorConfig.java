package com.mickey.core.advice.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author J·K
 * @description: 定义日志切面
 * @date 2021/5/26 2:55 下午
 */
@Slf4j
@Configuration
@ConditionalOnProperty({"aspect.pointcut.enabled"})
public class ConfigurableAdvisorConfig {

    @Value("${aspect.pointcut.execution:execution(public * com.mickey.controller..*.*(..))}")
    private String pointcut;

    @Bean
    public AspectJExpressionPointcutAdvisor configurabledvisor() {
        log.info("【pointcut】:{}",pointcut);
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(pointcut);
        advisor.setAdvice(new WebLogAspect());
        return advisor;
    }
}
