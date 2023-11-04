package cn.ppxytest.designmodewang.conf;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置链接
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        setSerializeConfig(redisTemplate, redisConnectionFactory);
        return  redisTemplate;
    }

    private void setSerializeConfig(RedisTemplate<String,Object> redisTemplate,RedisConnectionFactory redisConnectionFactory){
        // 普通 Key 和 HashKey采用StringRedisSerializer进行序列化
        StringRedisSerializer redisKeySerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisKeySerializer);
        redisTemplate.setHashKeySerializer(redisKeySerializer);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        Jackson2JsonRedisSerializer<?> redisValueSerizer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);

        // 普通 Value 与 Hash类型的Value采用jackson方式进行序列化
        redisTemplate.setValueSerializer(redisValueSerizer);
        redisTemplate.setHashValueSerializer(redisValueSerizer);
        redisTemplate.afterPropertiesSet();
    }
}
