package com.cavalry.common.client.redis;

import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseJedisClient implements JedisCommands, BasicCommands, MultiKeyCommands {

    private JedisPool jedisPool;

    public BaseJedisClient() {
    }

    public BaseJedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    interface JedisOp<T> {
        T op(Jedis jedis);
    }

    private <T> T execute(JedisOp<T> op) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return op.op(jedis);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String ping() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.ping();
            }
        });
    }

    @Override
    public String quit() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.quit();
            }
        });
    }

    @Override
    public String flushDB() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.flushDB();
            }
        });
    }

    @Override
    public Long dbSize() {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.dbSize();
            }
        });
    }

    @Override
    public String select(final int index) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.select(index);
            }
        });
    }

    @Override
    public String flushAll() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.flushAll();
            }
        });
    }

    @Override
    public String auth(final String password) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.auth(password);
            }
        });
    }

    @Override
    public String save() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.save();
            }
        });
    }

    @Override
    public String bgsave() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.bgsave();
            }
        });
    }

    @Override
    public String bgrewriteaof() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.bgrewriteaof();
            }
        });
    }

    @Override
    public Long lastsave() {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.lastsave();
            }
        });
    }

    @Override
    public String shutdown() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.shutdown();
            }
        });
    }

    @Override
    public String info() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.info();
            }
        });
    }

    @Override
    public String info(final String section) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.info(section);
            }
        });
    }

    @Override
    public String slaveof(final String host, final int port) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.slaveof(host, port);
            }
        });
    }

    @Override
    public String slaveofNoOne() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.slaveofNoOne();
            }
        });
    }

    @Override
    public Long getDB() {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.getDB();
            }
        });
    }

    @Override
    public String debug(final DebugParams params) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.debug(params);
            }
        });
    }

    @Override
    public String configResetStat() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.configResetStat();
            }
        });
    }

    @Override
    public Long waitReplicas(final int replicas, final long timeout) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.waitReplicas(replicas, timeout);
            }
        });
    }

    @Override
    public String set(final String key, final String value) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.set(key, value);
            }
        });
    }

    @Override
    public String set(final String key, final String value, final String nxxx, final String expx, final long time) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.set(key, value, nxxx, expx, time);
            }
        });
    }

    @Override
    public String get(final String key) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    @Override
    public Boolean exists(final String key) {
        return execute(new JedisOp<Boolean>() {
            @Override
            public Boolean op(Jedis jedis) {
                return jedis.exists(key);
            }
        });
    }

    @Override
    public Long persist(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.persist(key);
            }
        });
    }

    @Override
    public String type(final String key) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.type(key);
            }
        });
    }

    @Override
    public Long expire(final String key, final int seconds) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.expire(key, seconds);
            }
        });
    }

    @Override
    public Long pexpire(final String key, final long milliseconds) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.pexpire(key, milliseconds);
            }
        });
    }

    @Override
    public Long expireAt(final String key, final long unixTime) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.expireAt(key, unixTime);
            }
        });
    }

    @Override
    public Long pexpireAt(final String key, final long millisecondsTimestamp) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.pexpireAt(key, millisecondsTimestamp);
            }
        });
    }

    @Override
    public Long ttl(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.ttl(key);
            }
        });
    }

    @Override
    public Boolean setbit(final String key, final long offset, final boolean value) {
        return execute(new JedisOp<Boolean>() {
            @Override
            public Boolean op(Jedis jedis) {
                return jedis.setbit(key, offset, value);
            }
        });
    }

    @Override
    public Boolean setbit(final String key, final long offset, final String value) {
        return execute(new JedisOp<Boolean>() {
            @Override
            public Boolean op(Jedis jedis) {
                return jedis.setbit(key, offset, value);
            }
        });
    }

    @Override
    public Boolean getbit(final String key, final long offset) {
        return execute(new JedisOp<Boolean>() {
            @Override
            public Boolean op(Jedis jedis) {
                return jedis.getbit(key, offset);
            }
        });
    }

    @Override
    public Long setrange(final String key, final long offset, final String value) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.setrange(key, offset, value);
            }
        });
    }

    @Override
    public String getrange(final String key, final long startOffset, final long endOffset) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.getrange(key, startOffset, endOffset);
            }
        });
    }

    @Override
    public String getSet(final String key, final String value) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.getSet(key, value);
            }
        });
    }

    @Override
    public Long setnx(final String key, final String value) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.setnx(key, value);
            }
        });
    }

    @Override
    public String setex(final String key, final int seconds, final String value) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.setex(key, seconds, value);
            }
        });
    }

    @Override
    public Long decrBy(final String key, final long integer) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.decrBy(key, integer);
            }
        });
    }

    @Override
    public Long decr(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.decr(key);
            }
        });
    }

    @Override
    public Long incrBy(final String key, final long integer) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.incrBy(key, integer);
            }
        });
    }

    @Override
    public Double incrByFloat(final String key, final double value) {
        return execute(new JedisOp<Double>() {
            @Override
            public Double op(Jedis jedis) {
                return jedis.incrByFloat(key, value);
            }
        });
    }

    @Override
    public Long incr(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.incr(key);
            }
        });
    }

    @Override
    public Long append(final String key, final String value) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.append(key, value);
            }
        });
    }

    @Override
    public String substr(final String key, final int start, final int end) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.substr(key, start, end);
            }
        });
    }

    @Override
    public Long hset(final String key, final String field, final String value) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.hset(key, field, value);
            }
        });
    }

    @Override
    public String hget(final String key, final String field) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.hget(key, field);
            }
        });
    }

    @Override
    public Long hsetnx(final String key, final String field, final String value) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.hsetnx(key, field, value);
            }
        });
    }

    @Override
    public String hmset(final String key, final Map<String, String> hash) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.hmset(key, hash);
            }
        });
    }

    @Override
    public List<String> hmget(final String key, final String... fields) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.hmget(key, fields);
            }
        });
    }

    @Override
    public Long hincrBy(final String key, final String field, final long value) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.hincrBy(key, field, value);
            }
        });
    }

    @Override
    public Boolean hexists(final String key, final String field) {
        return execute(new JedisOp<Boolean>() {
            @Override
            public Boolean op(Jedis jedis) {
                return jedis.hexists(key, field);
            }
        });
    }

    @Override
    public Long hdel(final String key, final String... field) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.hdel(key, field);
            }
        });
    }

    @Override
    public Long hlen(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.hlen(key);
            }
        });
    }

    @Override
    public Set<String> hkeys(final String key) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.hkeys(key);
            }
        });
    }

    @Override
    public List<String> hvals(final String key) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.hvals(key);
            }
        });
    }

    @Override
    public Map<String, String> hgetAll(final String key) {
        return execute(new JedisOp<Map<String, String>>() {
            @Override
            public Map<String, String> op(Jedis jedis) {
                return jedis.hgetAll(key);
            }
        });
    }

    @Override
    public Long rpush(final String key, final String... string) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.rpush(key, string);
            }
        });
    }

    @Override
    public Long lpush(final String key, final String... string) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.lpush(key, string);
            }
        });
    }

    @Override
    public Long llen(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.llen(key);
            }
        });
    }

    @Override
    public List<String> lrange(final String key, final long start, final long end) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.lrange(key, start, end);
            }
        });
    }

    @Override
    public String ltrim(final String key, final long start, final long end) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.ltrim(key, start, end);
            }
        });
    }

    @Override
    public String lindex(final String key, final long index) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.lindex(key, index);
            }
        });
    }

    @Override
    public String lset(final String key, final long index, final String value) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.lset(key, index, value);
            }
        });
    }

    @Override
    public Long lrem(final String key, final long count, final String value) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.lrem(key, count, value);
            }
        });
    }

    @Override
    public String lpop(final String key) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.lpop(key);
            }
        });
    }

    @Override
    public String rpop(final String key) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.rpop(key);
            }
        });
    }

    @Override
    public Long sadd(final String key, final String... member) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.sadd(key, member);
            }
        });
    }

    @Override
    public Set<String> smembers(final String key) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.smembers(key);
            }
        });
    }

    @Override
    public Long srem(final String key, final String... member) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.srem(key, member);
            }
        });
    }

    @Override
    public String spop(final String key) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.spop(key);
            }
        });
    }

    @Override
    public Set<String> spop(final String key, final long count) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.spop(key, count);
            }
        });
    }

    @Override
    public Long scard(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.scard(key);
            }
        });
    }

    @Override
    public Boolean sismember(final String key, final String member) {
        return execute(new JedisOp<Boolean>() {
            @Override
            public Boolean op(Jedis jedis) {
                return jedis.sismember(key, member);
            }
        });
    }

    @Override
    public String srandmember(final String key) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.srandmember(key);
            }
        });
    }

    @Override
    public List<String> srandmember(final String key, final int count) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.srandmember(key, count);
            }
        });
    }

    @Override
    public Long strlen(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.strlen(key);
            }
        });
    }

    @Override
    public Long zadd(final String key, final double score, final String member) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zadd(key, score, member);
            }
        });
    }

    @Override
    public Long zadd(final String key, final Map<String, Double> scoreMembers) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zadd(key, scoreMembers);
            }
        });
    }

    @Override
    public Set<String> zrange(final String key, final long start, final long end) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrange(key, start, end);
            }
        });
    }

    @Override
    public Long zrem(final String key, final String... member) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zrem(key, member);
            }
        });
    }

    @Override
    public Double zincrby(final String key, final double score, final String member) {
        return execute(new JedisOp<Double>() {
            @Override
            public Double op(Jedis jedis) {
                return jedis.zincrby(key, score, member);
            }
        });
    }

    @Override
    public Long zrank(final String key, final String member) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zrank(key, member);
            }
        });
    }

    @Override
    public Long zrevrank(final String key, final String member) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zrevrank(key, member);
            }
        });
    }

    @Override
    public Set<String> zrevrange(final String key, final long start, final long end) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrevrange(key, start, end);
            }
        });
    }

    @Override
    public Set<Tuple> zrangeWithScores(final String key, final long start, final long end) {
        return execute(new JedisOp<Set<Tuple>>() {
            @Override
            public Set<Tuple> op(Jedis jedis) {
                return jedis.zrangeWithScores(key, start, end);
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end) {
        return execute(new JedisOp<Set<Tuple>>() {
            @Override
            public Set<Tuple> op(Jedis jedis) {
                return jedis.zrevrangeWithScores(key, start, end);
            }
        });
    }

    @Override
    public Long zcard(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zcard(key);
            }
        });
    }

    @Override
    public Double zscore(final String key, final String member) {
        return execute(new JedisOp<Double>() {
            @Override
            public Double op(Jedis jedis) {
                return jedis.zscore(key, member);
            }
        });
    }

    @Override
    public List<String> sort(final String key) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.sort(key);
            }
        });
    }

    @Override
    public List<String> sort(final String key, final SortingParams sortingParameters) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.sort(key, sortingParameters);
            }
        });
    }

    @Override
    public Long zcount(final String key, final double min, final double max) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zcount(key, min, max);
            }
        });
    }

    @Override
    public Long zcount(final String key, final String min, final String max) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zcount(key, min, max);
            }
        });
    }

    @Override
    public Set<String> zrangeByScore(final String key, final double min, final double max) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }
        });
    }

    @Override
    public Set<String> zrangeByScore(final String key, final String min, final String max) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }
        });
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrevrangeByScore(key, max, min);
            }
        });
    }

    @Override
    public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset, final int count) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max, offset, count);
            }
        });
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final String max, final String min) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrevrangeByScore(key, max, min);
            }
        });
    }

    @Override
    public Set<String> zrangeByScore(final String key, final String min, final String max, final int offset, final int count) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max, offset, count);
            }
        });
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final double max, final double min, final int offset, final int count) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrevrangeByScore(key, max, min, offset, count);
            }
        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
        return execute(new JedisOp<Set<Tuple>>() {
            @Override
            public Set<Tuple> op(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max);
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min) {
        return execute(new JedisOp<Set<Tuple>>() {
            @Override
            public Set<Tuple> op(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min);
            }
        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count) {
        return execute(new JedisOp<Set<Tuple>>() {
            @Override
            public Set<Tuple> op(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        });
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final String max, final String min, final int offset, final int count) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrevrangeByScore(key, max, min, offset, count);
            }
        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max) {
        return execute(new JedisOp<Set<Tuple>>() {
            @Override
            public Set<Tuple> op(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max);
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min) {
        return execute(new JedisOp<Set<Tuple>>() {
            @Override
            public Set<Tuple> op(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min);
            }
        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max, final int offset, final int count) {
        return execute(new JedisOp<Set<Tuple>>() {
            @Override
            public Set<Tuple> op(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min, final int offset, final int count) {
        return execute(new JedisOp<Set<Tuple>>() {
            @Override
            public Set<Tuple> op(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String max, final String min, final int offset, final int count) {
        return execute(new JedisOp<Set<Tuple>>() {
            @Override
            public Set<Tuple> op(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }
        });
    }

    @Override
    public Long zremrangeByRank(final String key, final long start, final long end) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zremrangeByRank(key, start, end);
            }
        });
    }

    @Override
    public Long zremrangeByScore(final String key, final double start, final double end) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }
        });
    }

    @Override
    public Long zremrangeByScore(final String key, final String start, final String end) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }
        });
    }

    @Override
    public Long zlexcount(final String key, final String min, final String max) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zlexcount(key, min, max);
            }
        });
    }

    @Override
    public Set<String> zrangeByLex(final String key, final String min, final String max) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrangeByLex(key, min, max);
            }
        });
    }

    @Override
    public Set<String> zrangeByLex(final String key, final String min, final String max, final int offset, final int count) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrangeByLex(key, min, max, offset, count);
            }
        });
    }

    @Override
    public Set<String> zrevrangeByLex(final String key, final String max, final String min) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrevrangeByLex(key, max, min);
            }
        });
    }

    @Override
    public Set<String> zrevrangeByLex(final String key, final String max, final String min, final int offset, final int count) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.zrevrangeByLex(key, max, min, offset, count);
            }
        });
    }

    @Override
    public Long zremrangeByLex(final String key, final String min, final String max) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zremrangeByLex(key, min, max);
            }
        });
    }

    @Override
    public Long linsert(final String key, final BinaryClient.LIST_POSITION where, final String pivot, final String value) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.linsert(key, where, pivot, value);
            }
        });
    }

    @Override
    public Long lpushx(final String key, final String... string) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.lpushx(key, string);
            }
        });
    }

    @Override
    public Long rpushx(final String key, final String... string) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.rpushx(key, string);
            }
        });
    }

    @Override
    public List<String> blpop(final String arg) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.blpop(arg);
            }
        });
    }

    @Override
    public List<String> blpop(final int timeout, final String key) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.blpop(timeout, key);
            }
        });
    }

    @Override
    public List<String> brpop(final String arg) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.brpop(arg);
            }
        });
    }

    @Override
    public List<String> brpop(final int timeout, final String key) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.brpop(timeout, key);
            }
        });
    }

    @Override
    public Long del(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.del(key);
            }
        });
    }

    @Override
    public String echo(final String string) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.echo(string);
            }
        });
    }

    @Override
    public Long move(final String key, final int dbIndex) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.move(key, dbIndex);
            }
        });
    }

    @Override
    public Long bitcount(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.bitcount(key);
            }
        });
    }

    @Override
    public Long bitcount(final String key, final long start, final long end) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.bitcount(key, start, end);
            }
        });
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(final String key, final int cursor) {
        return execute(new JedisOp<ScanResult<Map.Entry<String, String>>>() {
            @Override
            public ScanResult<Map.Entry<String, String>> op(Jedis jedis) {
                return jedis.hscan(key, cursor);
            }
        });
    }

    @Override
    public ScanResult<String> sscan(final String key, final int cursor) {
        return execute(new JedisOp<ScanResult<String>>() {
            @Override
            public ScanResult<String> op(Jedis jedis) {
                return jedis.sscan(key, cursor);
            }
        });
    }

    @Override
    public ScanResult<Tuple> zscan(final String key, final int cursor) {
        return execute(new JedisOp<ScanResult<Tuple>>() {
            @Override
            public ScanResult<Tuple> op(Jedis jedis) {
                return jedis.zscan(key, cursor);
            }
        });
    }

    @Override
    public ScanResult<Map.Entry<String, String>> hscan(final String key, final String cursor) {
        return execute(new JedisOp<ScanResult<Map.Entry<String, String>>>() {
            @Override
            public ScanResult<Map.Entry<String, String>> op(Jedis jedis) {
                return jedis.hscan(key, cursor);
            }
        });
    }

    @Override
    public ScanResult<String> sscan(final String key, final String cursor) {
        return execute(new JedisOp<ScanResult<String>>() {
            @Override
            public ScanResult<String> op(Jedis jedis) {
                return jedis.sscan(key, cursor);
            }
        });
    }

    @Override
    public ScanResult<Tuple> zscan(final String key, final String cursor) {
        return execute(new JedisOp<ScanResult<Tuple>>() {
            @Override
            public ScanResult<Tuple> op(Jedis jedis) {
                return jedis.zscan(key, cursor);
            }
        });
    }

    @Override
    public Long pfadd(final String key, final String... elements) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.pfadd(key, elements);
            }
        });
    }

    @Override
    public long pfcount(final String key) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.pfcount(key);
            }
        });
    }

    @Override
    public Long del(final String... keys) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.del(keys);
            }
        });
    }

    @Override
    public List<String> blpop(final int timeout, final String... keys) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.blpop(timeout, keys);
            }
        });
    }

    @Override
    public List<String> brpop(final int timeout, final String... keys) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.brpop(timeout, keys);
            }
        });
    }

    @Override
    public List<String> blpop(final String... args) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.blpop(args);
            }
        });
    }

    @Override
    public List<String> brpop(final String... args) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.brpop(args);
            }
        });
    }

    @Override
    public Set<String> keys(final String pattern) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.keys(pattern);
            }
        });
    }

    @Override
    public List<String> mget(final String... keys) {
        return execute(new JedisOp<List<String>>() {
            @Override
            public List<String> op(Jedis jedis) {
                return jedis.mget(keys);
            }
        });
    }

    @Override
    public String mset(final String... keysvalues) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.mset(keysvalues);
            }
        });
    }

    @Override
    public Long msetnx(final String... keysvalues) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.msetnx(keysvalues);
            }
        });
    }

    @Override
    public String rename(final String oldkey, final String newkey) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.rename(oldkey, newkey);
            }
        });
    }

    @Override
    public Long renamenx(final String oldkey, final String newkey) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.renamenx(oldkey, newkey);
            }
        });
    }

    @Override
    public String rpoplpush(final String srckey, final String dstkey) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.rpoplpush(srckey, dstkey);
            }
        });
    }

    @Override
    public Set<String> sdiff(final String... keys) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.sdiff(keys);
            }
        });
    }

    @Override
    public Long sdiffstore(final String dstkey, final String... keys) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.sdiffstore(dstkey, keys);
            }
        });
    }

    @Override
    public Set<String> sinter(final String... keys) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.sinter(keys);
            }
        });
    }

    @Override
    public Long sinterstore(final String dstkey, final String... keys) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.sinterstore(dstkey, keys);
            }
        });
    }

    @Override
    public Long smove(final String srckey, final String dstkey, final String member) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.smove(srckey, dstkey, member);
            }
        });
    }

    @Override
    public Long sort(final String key, final SortingParams sortingParameters, final String dstkey) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.sort(key, sortingParameters, dstkey);
            }
        });
    }

    @Override
    public Long sort(final String key, final String dstkey) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.sort(key, dstkey);
            }
        });
    }

    @Override
    public Set<String> sunion(final String... keys) {
        return execute(new JedisOp<Set<String>>() {
            @Override
            public Set<String> op(Jedis jedis) {
                return jedis.sunion(keys);
            }
        });
    }

    @Override
    public Long sunionstore(final String dstkey, final String... keys) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.sunionstore(dstkey, keys);
            }
        });
    }

    @Override
    public String watch(final String... keys) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.watch(keys);
            }
        });
    }

    @Override
    public String unwatch() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.unwatch();
            }
        });
    }

    @Override
    public Long zinterstore(final String dstkey, final String... sets) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zinterstore(dstkey, sets);
            }
        });
    }

    @Override
    public Long zinterstore(final String dstkey, final ZParams params, final String... sets) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zinterstore(dstkey, params, sets);
            }
        });
    }

    @Override
    public Long zunionstore(final String dstkey, final String... sets) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zunionstore(dstkey, sets);
            }
        });
    }

    @Override
    public Long zunionstore(final String dstkey, final ZParams params, final String... sets) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.zunionstore(dstkey, params, sets);
            }
        });
    }

    @Override
    public String brpoplpush(final String source, final String destination, final int timeout) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.brpoplpush(source, destination, timeout);
            }
        });
    }

    @Override
    public Long publish(final String channel, final String message) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.publish(channel, message);
            }
        });
    }

    @Override
    public void subscribe(final JedisPubSub jedisPubSub, final String... channels) {
        execute(new JedisOp<Object>() {
            @Override
            public Object op(Jedis jedis) {
                jedis.subscribe(jedisPubSub, channels);
                return null;
            }
        });
    }

    @Override
    public void psubscribe(final JedisPubSub jedisPubSub, final String... patterns) {
        execute(new JedisOp<Object>() {
            @Override
            public Object op(Jedis jedis) {
                jedis.psubscribe(jedisPubSub, patterns);
                return null;
            }
        });
    }

    @Override
    public String randomKey() {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.randomKey();
            }
        });
    }

    @Override
    public Long bitop(final BitOP op, final String destKey, final String... srcKeys) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.bitop(op, destKey, srcKeys);
            }
        });
    }

    @Override
    public ScanResult<String> scan(final int cursor) {
        return execute(new JedisOp<ScanResult<String>>() {
            @Override
            public ScanResult<String> op(Jedis jedis) {
                return jedis.scan(cursor);
            }
        });
    }

    @Override
    public ScanResult<String> scan(final String cursor) {
        return execute(new JedisOp<ScanResult<String>>() {
            @Override
            public ScanResult<String> op(Jedis jedis) {
                return jedis.scan(cursor);
            }
        });
    }

    @Override
    public String pfmerge(final String destkey, final String... sourcekeys) {
        return execute(new JedisOp<String>() {
            @Override
            public String op(Jedis jedis) {
                return jedis.pfmerge(destkey, sourcekeys);
            }
        });
    }

    @Override
    public long pfcount(final String... keys) {
        return execute(new JedisOp<Long>() {
            @Override
            public Long op(Jedis jedis) {
                return jedis.pfcount(keys);
            }
        });
    }
}
