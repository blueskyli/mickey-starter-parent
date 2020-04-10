package com.mickey.redis.utils;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author J·K
 * @Description: https://www.cnblogs.com/ningJJ/p/7808482.html
 * @date 2018/7/30 15:08
 */
public class RedisTemplateUtil
{
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    private static RedisTemplateUtil cacheUtils;

    @PostConstruct
    public void init() {

        cacheUtils = this;
        cacheUtils.redisTemplate = redisTemplate;
    }

    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
     * 如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
     * @param key
     * @param value
     * @return 追加 value 之后， key 中字符串的长度
     */
    public static Integer stringAppendString(String key,String value){
        return cacheUtils.redisTemplate.opsForValue().append(key,value);
    }

    /**
     * 设置指定键的值
     * @param key
     * @param value
     */
    public static void stringSetString(String key, String value){
        cacheUtils.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取指定键的值
     * @param key
     * @return
     */
    public static String stringGetStringByKey(String key){
        return cacheUtils.redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置键的字符串值并返回其旧值
     * @param key
     * @param value
     * @return
     */
    public static String stringGetAndSet(String key, String value){
        return cacheUtils.redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 使用键和到期时间来设置值,时间单位默认为毫秒
     * @param key
     * @param value
     * @param timeout
     */
    public static void stringSetValueAndExpireTime(String key, String value, long timeout){
        cacheUtils.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 使用键和到期时间来设置值
     * @param key
     * @param value
     * @param timeout
     */
    public static void stringSetValueAndExpireTime(String key, String value, long timeout,TimeUnit timeUnit){
        cacheUtils.redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }


    /**
     * 获取存储在键上的字符串的子字符串
     * @param key
     * @param start
     * @param end
     * @return 截取后的子字符串
     */
    public static String stringGetSubStringFromString(String key, long start, long end){
        return cacheUtils.redisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 将键的整数值按给定的长整型数值增加
     * @param key
     * @param delta
     * @return 返回增长后的结果值
     */
    public static Long stringIncrementLongString(String key, Long delta){
        return cacheUtils.redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 将键的整数值按给定的浮点型数值增加
     * @param key
     * @param delta
     * @return 返回增长后的结果值
     */
    public static Double stringIncrementDoubleString(String key, Double delta){
        return cacheUtils.redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 设置超时时间
     * @param key
     * @param seconds
     */
    public static void expire(String key, int seconds){
        cacheUtils.redisTemplate.execute((RedisCallback<Boolean>)x->x.expire(key.getBytes(),seconds));
    }

    /**
     * 将 key的值保存为 value ，当且仅当 key 不存在。 若给定的 key 已经存在，则 SETNX 不做任何动作。 SETNX 是『SET if
     * Not eXists』(如果不存在，则 SET)的简写。 <br>
     * 保存成功，返回 true <br>
     * 保存失败，返回 false
     */
    public static boolean saveNX(String key, String val) {

        /** 设置成功，返回 1 设置失败，返回 0 **/
        return cacheUtils.redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            return connection.setNX(key.getBytes(), val.getBytes());
        });

    }

    /**
     * 将 key的值保存为 value ，当且仅当 key 不存在。 若给定的 key 已经存在，则 SETNX 不做任何动作。 SETNX 是『SET if
     * Not eXists』(如果不存在，则 SET)的简写。 <br>
     * 保存成功，返回 true <br>
     * 保存失败，返回 false
     *
     * @param key
     * @param val
     * @param expire 超时时间
     * @return 保存成功，返回 true 否则返回 false
     */
    public static boolean saveNX(String key, String val, int expire) {

        boolean ret = saveNX(key, val);
        if (ret) {
            cacheUtils.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return ret;
    }

    /**
     * 删除key
     * @param key
     * @return
     */
    public static Boolean delete(String key){
        return cacheUtils.redisTemplate.delete(key);
    }

    /**
     * 根据key增长 ，计数器
     * @param key
     * @return
     */
    public static long incr(String key) {

        return cacheUtils.redisTemplate.execute((RedisCallback<Long>) connection -> {
            return connection.incr(key.getBytes());
        });
    }
}
