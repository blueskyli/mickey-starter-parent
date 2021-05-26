package com.mickey.openfeign.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import okhttp3.ConnectionPool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author J·K
 * @description: Feign Auto Config
 * @date 2021/5/26 2:03 下午
 */
@Configuration
public class FeignAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Logger.Level.class)
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    @ConditionalOnProperty({"feign.okhttp.enabled"})
    public okhttp3.OkHttpClient okHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool())
            .build();
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignErrorDecoder();
    }
}
