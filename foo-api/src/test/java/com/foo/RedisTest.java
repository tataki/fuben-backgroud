package com.foo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest extends BaseTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void set(){
    redisTemplate.opsForValue().set("test","1");
    }
    @Test
    public void get(){
        System.out.println(redisTemplate.opsForValue().get("test"));
    }
    @Test
    public void del(){
    redisTemplate.delete("test");
    }
}
