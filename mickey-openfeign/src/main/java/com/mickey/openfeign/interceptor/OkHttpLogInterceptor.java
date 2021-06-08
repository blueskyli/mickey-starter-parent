package com.mickey.openfeign.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * @author J·K
 * @description: OkHttpLogInterceptor
 * @date 2021/5/27 12:44 下午
 */
@Slf4j
public class OkHttpLogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.currentTimeMillis();//请求发起的时间
//        log.info(String.format("发送请求 %s on %s%n%s",
//            request.url(), chain.connection(), request.headers()));
        log.info("【OkHttpLogInterceptor】{}------>开始时间:{}", request.url(), t1);
        Response response = chain.proceed(request);
        long t2 = System.currentTimeMillis();//收到响应的时间
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
//        ResponseBody responseBody = response.peekBody(1024 * 1024);
//        log.info(String.format("【OkHttpLogInterceptor】: [%s] %n返回json:【%s】 %.1fms%n%s",
//            response.request().method(),
//            responseBody.string(),
//            (t2 - t1) / 1e6d,
//            response.headers()));
        log.info("【OkHttpLogInterceptor】{}------>结束时间:{},调用时长:{}ms",
            request.url(), t2, t2 - t1);
        return response;
    }
}
