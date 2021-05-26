package com.mickey.openfeign.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.mickey.core.exception.NoveServiceException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author J·K
 * @Description: FeignErrorDecoder
 * @date 2020/6/30 2:39 上午
 */
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() != null) {
                String body = Util.toString(response.body().asReader());
                ExceptionInfo exceptionInfo = JSON.parseObject(body, new TypeReference<ExceptionInfo>() {
                });
                if (StringUtils.isNotBlank(exceptionInfo.getMessage())) {
                    String[] messages = exceptionInfo.getMessage().split("\\|");
                    if (messages.length > 1) {
                        return new NoveServiceException(messages[0], messages[1]);
                    }
                }
                return new NoveServiceException(500, exceptionInfo.toString());
            }
        } catch (Exception e) {
            return new NoveServiceException(500, e.getMessage());
        }
        return new NoveServiceException(500, "FeignErrorDecoder error");
    }

    @Data
    public static class ExceptionInfo {
        private Long timestamp;

        private Integer status;

        private String exception;

        private String message;

        private String path;

        private String error;

    }
}
