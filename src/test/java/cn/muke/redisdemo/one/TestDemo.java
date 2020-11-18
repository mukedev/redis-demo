package cn.muke.redisdemo.one;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * @author zhangYu
 * @program redis-demo
 * @description redis测试
 * @create 2020-11-18 19:52
 **/
public class TestDemo {
    private final String PASSWORD = "foob1ared@#QAZ";
    private JedisPool jedisPool;

    @Before
    public void getConnect() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大实例数
        config.setMaxTotal(20);
        // 最大空闲数
        config.setMaxIdle(10);
        // 最小空闲数
        config.setMinIdle(5);
        // 最大等待时间（毫秒）
        config.setMaxWaitMillis(5000);

        jedisPool = new JedisPool(config, "10.123.212.40");
    }

    @Test
    public void set() {
        Jedis jedis = jedisPool.getResource();
        jedis.auth(PASSWORD);
        try {
            jedis.set("hello", "zhangYu-client");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    @Test
    public void get() {
        Jedis jedis = jedisPool.getResource();
        jedis.auth(PASSWORD);
        try {
            String str = jedis.get("hello");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    @Test
    public void setnx() {
        Jedis jedis = jedisPool.getResource();
        jedis.auth(PASSWORD);
        try {
            jedis.setnx("hello1", "测试");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    @Test
    public void substr() {
        Jedis jedis = jedisPool.getResource();
        jedis.auth(PASSWORD);

        try {
            String substr = jedis.substr("hello", 0, 6);
            System.out.println(substr);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    @Test
    public void strlen(){
        Jedis jedis = jedisPool.getResource();
        jedis.auth(PASSWORD);

        try {
            Long strlen = jedis.strlen("hello".getBytes());
            System.out.println(strlen);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    @Test
    public void mset(){
        Jedis jedis = jedisPool.getResource();
        jedis.auth(PASSWORD);

        try {
            jedis.mset("str1","val1","str2","val2");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    @Test
    public void mget(){
        Jedis jedis = jedisPool.getResource();
        jedis.auth(PASSWORD);

        try {
            List<String> mget = jedis.mget("str1", "str2");

            for (String s : mget) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }


    @After
    public void close() {
        jedisPool.close();
    }
}
