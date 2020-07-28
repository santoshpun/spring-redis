package com.santosh.springredis.bean;

import com.santosh.springredis.cache.CacheObject;
import com.santosh.springredis.cache.UserTokenRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NamePrintingTask implements Runnable {
    private UserTokenRepository userTokenRepository;
    private String id;
    private String name;

    public NamePrintingTask(UserTokenRepository userTokenRepository, String id, String name) {
        this.userTokenRepository = userTokenRepository;

        this.id = id;
        this.name = name;
    }

    @Override
    public void run() {
        CacheObject cacheObject = new CacheObject();
        cacheObject.setId(id);
        cacheObject.setName(name);
        userTokenRepository.create(cacheObject);

        CacheObject retrievedObject = userTokenRepository.get(id);
        log.info("Retrieved object : " + retrievedObject);
    }
}
