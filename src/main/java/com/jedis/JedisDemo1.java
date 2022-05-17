package com.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisDemo1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        System.out.println(jedis.ping());
    }
    @Test
    public void demo1(){
        Jedis jedis = new Jedis("localhost",6379);
        System.out.println(jedis.ping());
        jedis.mset("k1","v1","k2","v2");
        List<String> mget = jedis.mget("k1","k2");
        System.out.println(mget);

    }
    @Test
    public void demo2(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.lpush("k1","lucy","mary","jack");
        List<String> k1 = jedis.lrange("k1", 0, -1);
        System.out.println(k1);
    }
    @Test
    public void demo3(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.sadd("name","lucy","jack","lucy");
        Set<String> name = jedis.smembers("name");
        System.out.println(name);
    }
    @Test
    public void demo4(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.hset("user","age","20");
        jedis.hset("user","name","lucy");
        System.out.println(jedis.hget("user", "name"));
    }
    @Test
    public void demo5(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.zadd("china",1000,"shanghai");
        Set<String> china = jedis.zrange("china", 0, -1);
        System.out.println(china);
    }
}
