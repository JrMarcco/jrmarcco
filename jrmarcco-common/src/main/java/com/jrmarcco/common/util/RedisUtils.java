package com.jrmarcco.common.util;

import com.jrmarcco.common.constant.RedisConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author hongjc
 * @version 1.0  2019/2/25
 */
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

    public static <T> T setStringValue(StringRedisTemplate rt, String key, T value) {
        var expire = RedisConstants.DEFAULT_EXPIRE + new Random().nextInt(60);
        var jsonValue = JsonUtils.toJson(value);
        try {
            if (!StringUtils.isEmpty(jsonValue)) {
                rt.opsForValue().set(key, jsonValue, expire, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("### Write redis string value error，key={}, value={} ###", key, jsonValue);
        }
        return value;
    }
}
