package com.santosh.springredis.config;

import com.santosh.springredis.util.YamlPropertySourceFactory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "redis")
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:redis.yml")
public class RedisProperties {

    private Boolean enabled;

    private String host;

    private int port;

    private String password;

    private int database;

    private int connectionTimeout;

    private int readTimeout;

    private RedisPoolConfigProperties pool;

    @Data
    public static class RedisPoolConfigProperties {

        private int maxTotal;

        private int maxIdle;

        private int minIdle;

        private int maxWaitMillis;

        private Boolean testOnBorrow;

        private Boolean testOnReturn;

        private Boolean testWhileIdle;

        private Boolean blockedWhenExhausted;

        private int numTestsPerEvictionRun;

        private int timeBetweenEvictionRunsMillis;

        private int minEvictableIdleTimeMillis;
    }
}
