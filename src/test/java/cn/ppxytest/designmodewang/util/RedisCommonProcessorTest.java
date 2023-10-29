package cn.ppxytest.designmodewang.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisCommonProcessorTest {

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Test
    void testSetAntGet() {
        redisCommonProcessor.set("test","hello");
        Object o = redisCommonProcessor.get("test");
        Assertions.assertNotNull(o);
    }

    @Test
    void testSet() throws InterruptedException {
        redisCommonProcessor.set("testTime","hello",2);
        Assertions.assertNotNull(redisCommonProcessor.get("testTime"));
        Thread.sleep(2300);
        Assertions.assertNull(redisCommonProcessor.get("testTime"));

    }
}