package com.jrmarcco.common.util;

import com.jrmarcco.common.constant.RedisConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author hongjc
 * @version 1.0  2019/2/25
 */
@SuppressWarnings("WeakerAccess")
@Slf4j
public class RedisUtils {

    public static <T> T getStringValue(StringRedisTemplate rt, Class<T> cls, String key) {
        try {
            return Optional.ofNullable(rt.opsForValue().get(key))
                    .map(json -> JsonUtils.parseJson(json, cls))
                    .orElse(null);
        } catch (Exception e) {
            log.error("### Read redis string value error，key={}，exception message：{} ###", key, e.getMessage());
        }
        return null;
    }

    /**
     * 设置String缓存，默认过期时间30分钟 + 60秒内随机值。
     *
     * @param rt    StringRedisTemplate
     * @param key   redis-key
     * @param value redis-value
     * @param <T>   实体类型信息
     * @return 返回缓存的具体对象
     */
    public static <T> T setStringValue(StringRedisTemplate rt, String key, T value) {
        var expire = RedisConstants.DEFAULT_EXPIRE + new Random().nextInt(60);
        return setStringValue(rt, key, value, expire);
    }

    /**
     * 设置String缓存。
     *
     * @param rt     StringRedisTemplate
     * @param key    redis-key
     * @param value  redis-value
     * @param expire 过期时间（单位：秒）
     * @param <T>    实体类型信息
     * @return 返回缓存的具体对象
     */
    public static <T> T setStringValue(StringRedisTemplate rt, String key, T value, long expire) {
        return setStringValue(rt, key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置String缓存。
     *
     * @param rt       StringRedisTemplate
     * @param key      redis-key
     * @param value    redis-value
     * @param expire   过期时间
     * @param timeUnit 过期时间单位
     * @param <T>      实体类型信息
     * @return 返回缓存的具体对象
     */
    public static <T> T setStringValue(StringRedisTemplate rt, String key, T value, long expire, TimeUnit timeUnit) {
        var jsonValue = JsonUtils.toJson(value);
        try {
            Assert.notNull(jsonValue, "Redis value must not be null.");
            rt.opsForValue().set(key, jsonValue, expire, timeUnit);
        } catch (Exception e) {
            log.error("### Write redis string value error，key={}, value={} ###", key, jsonValue);
        }
        return value;
    }

    /**
     * 设置String缓存，永久有效。
     *
     * @param rt    StringRedisTemplate
     * @param key   redis-key
     * @param value redis-value
     * @param <T>   实体类型信息
     * @return 返回缓存的具体对象
     */
    public static <T> T setStringValuePermanent(StringRedisTemplate rt, String key, T value) {
        var jsonValue = JsonUtils.toJson(value);
        try {
            Assert.notNull(jsonValue, "Redis value must not be null.");
            rt.opsForValue().set(key, jsonValue);
        } catch (Exception e) {
            log.error("### Write redis string value error，key={}, value={} ###", key, jsonValue);
        }
        return value;
    }
}
