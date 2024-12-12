package com.bob;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet() {
       ValueOperations<String, String> vop =  stringRedisTemplate.opsForValue();
       vop.set("username", "shanghai", 1, TimeUnit.SECONDS);
    }

    @Test
    public void getSet() {
        ValueOperations<String, String> vop =  stringRedisTemplate.opsForValue();
        System.out.println(vop.get("username"));
    }

    @Test
    public void deleteSet() {
        ValueOperations<String, String> vop =  stringRedisTemplate.opsForValue();
        vop.getOperations().delete("username");
        System.out.println(vop.get("username"));
    }

}
