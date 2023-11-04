package cn.ppxytest.designmodewang.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCommonProcessor {
    // 依赖注入我们定义好的 RedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public Object get(String key) {
        if (key == null) {
            throw new UnsupportedOperationException("Redis key could not be null!");
        }
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 向Redis 中存入 key：value 数据对
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    public void remove(String orderId) {
        redisTemplate.delete(orderId);
    }
}
