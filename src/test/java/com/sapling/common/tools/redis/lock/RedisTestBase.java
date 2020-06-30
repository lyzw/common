package com.sapling.common.tools.redis.lock;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2020/4/26
 * @since 1.0
 */
public class RedisTestBase {

    private static String host = "129.204.238.77";
    private static Integer port = 16379;


    static GenericObjectPoolConfig config = new GenericObjectPoolConfig();
    static JedisPool jedisPool;
    static {
        jedisPool = new JedisPool(config,host,port,1000,"foobared");
    }

    public static Jedis createJedis(){
        return jedisPool.getResource();
    }
}
