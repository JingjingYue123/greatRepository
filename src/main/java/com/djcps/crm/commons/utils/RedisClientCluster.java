package com.djcps.crm.commons.utils;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

/**
 * Redis客户端集群版
 * 
 *
 */

public class RedisClientCluster implements RedisClient {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String set(String key, String value) {
        String result = jedisCluster.set(key, value);
        return result;
    }

    @Override
    public String get(String key) {
        String result = jedisCluster.get(key);
        return result;
    }

    @Override
    public Long del(String key) {
        Long result = jedisCluster.del(key);
        return result;
    }

    @Override
    public Long hset(String key, String field, String value) {
        Long result = jedisCluster.hsetnx(key, field, value);
        return result;
    }

    @Override
    public String hget(String key, String field) {
        String result = jedisCluster.hget(key, field);
        return result;
    }

    @Override
    public Long hdel(String key, String... fields) {
        Long result = jedisCluster.hdel(key, fields);
        return result;
    }

    @Override
    public Long setnx(String key, String value) {
        Long result = jedisCluster.setnx(key, value);
        return result;
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        Long result = jedisCluster.hsetnx(key, field, value);
        return result;
    }

    @Override
    public Long expire(String key, int seconds) {
        Long result = jedisCluster.expire(key, seconds);
        return result;
    }

    @Override
    public Long incr(String key) {
        Long result = jedisCluster.incr(key);
        return result;
    }

    @Override
    public Long decr(String key) {
        Long result = jedisCluster.decr(key);
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Boolean result = jedisCluster.exists(key);
        return result;
    }

    @Override
    public Long persist(String key) {
        Long result = jedisCluster.persist(key);
        return result;
    }

    @Override
    public Long ttl(String key) {
        Long result = jedisCluster.ttl(key);
        return result;
    }

    @Override
    public Long lpush(String key, String... strings) {
        Long result = jedisCluster.lpush(key, strings);
        return result;
    }

    @Override
    public String rpop(String key) {
        String result = jedisCluster.rpop(key);
        return result;
    }

    @Override
    public Long sadd(String key, String... strings) {
        Long result = jedisCluster.sadd(key, strings);
        return result;
    }

    @Override
    public Set<String> smembers(String key) {
        Set<String> result = jedisCluster.smembers(key);
        return result;
    }

}
