package com.santosh.springredis.cache;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
@SuppressWarnings({"Duplicates"})
@Component
public class UserTokenRepository {

    private RedisTemplate redisTemplate;

    private HashOperations hashOperations;
    private String keyPrefix = "HASH_";
    private long ttlInMinutes;

    @Autowired
    public UserTokenRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();

        this.ttlInMinutes = 10;
    }

    public void create(CacheObject user) {
        String key = keyPrefix + user.getId();
        String hashKey = user.getId();

        hashOperations.put(key, hashKey, user);
        redisTemplate.expire(key, ttlInMinutes, TimeUnit.MINUTES);

        log.info(String.format("Token %s will expire at %s seconds", key, redisTemplate.getExpire(key)));
        log.info(String.format("Token %s saved for user id %s", key, user.getId()));
    }

    public CacheObject get(String token) {
        String key = keyPrefix + token;
        String hashKey = token;

        CacheObject cachedTokenDetail = (CacheObject) hashOperations.get(key, hashKey);
        log.info(String.format("Token %s read for user id %s", key, cachedTokenDetail.getId()));
        return cachedTokenDetail;
    }

    public void getAll() {
        RedisConnection redisConnection = null;
        try {
            redisConnection = redisTemplate.getConnectionFactory().getConnection();

            String query = keyPrefix;

            ScanOptions scanOptions = ScanOptions.scanOptions()
                    .match(query + "*")
                    .build();

            Cursor c = redisConnection.scan(scanOptions);

            while (c.hasNext()) {
                byte[] next = (byte[]) c.next();
                String key = new String(next, StandardCharsets.UTF_8);
                log.info("key : {}", key);
            }
        } finally {
            redisConnection.close();
        }
    }

    public void update(CacheObject user) {
        String key = keyPrefix + user.getId();
        String hashKey = user.getId();

        hashOperations.put(key, hashKey, user);
        redisTemplate.expire(key, ttlInMinutes, TimeUnit.MINUTES);

        log.info(String.format("Token %s will expire at %s seconds", key, redisTemplate.getExpire(key)));
        log.info(String.format("Token %s saved for user id %s", key, user.getId()));
    }

    public void delete(String token) {
        String key = keyPrefix + token;
        String hashKey = token;

        hashOperations.delete(key, hashKey);
        log.info(String.format("Token %s deleted", hashKey));
    }
}