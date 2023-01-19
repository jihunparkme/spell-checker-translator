package com.aaron.spellcheckertranslator.commin.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@RequiredArgsConstructor
@Configuration
public class EmbeddedRedisConfig {
    private final RedisConfig redisConfig;
    private RedisServer redisServer;

    @PostConstruct
    public void redisServer(){
        redisServer = new RedisServer(redisConfig.getPort());
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis(){
        if(redisServer != null){
            redisServer.stop();
        }
    }
}
