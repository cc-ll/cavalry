package com.cavalry.common.client.redis;

import com.google.common.collect.Sets;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisPool;

public class BaseJedisClientTest {

    private BaseJedisClient client;

    @Before
    public void setUp() throws Exception {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxWaitMillis(-1);
        poolConfig.setMaxTotal(200);
        poolConfig.setMinIdle(8);
        poolConfig.setMaxIdle(100);
        JedisPool pool = new JedisPool(poolConfig, "10.198.184.148", 3002, 1000);
        client = new BaseJedisClient(pool);
    }

    @Test
    public void testName() throws Exception {
        client.sadd("foo1", "bar1", "bar2", "bar3");
        client.sadd("foo2", "bar1", "bar2");
        client.sadd("foo3", "bar1");
        Assert.assertEquals(Sets.newHashSet("bar1"), client.sinter("foo1", "foo2", "foo3"));
        Assert.assertEquals(Sets.newHashSet("bar1", "bar2", "bar3"), client.sunion("foo1", "foo2", "foo3"));
    }
}