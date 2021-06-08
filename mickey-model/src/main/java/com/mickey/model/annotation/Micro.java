package com.mickey.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author J·K
 * @Description: 给微服务提供的注解
 * @date 2020/11/12 11:36 上午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Micro {

    String value() default "";
}
