package com.itheima;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testRedisSet() {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("testKey", "testValue");
    }

    @Test
    void testRedisGet() {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        String value = stringStringValueOperations.get("testKey");
        logger.info("value=" + value);
    }
}
