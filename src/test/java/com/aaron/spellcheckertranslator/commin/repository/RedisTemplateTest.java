package com.aaron.spellcheckertranslator.commin.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("prd")
class RedisTemplateTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void string_test() {
        String key = "key01";

        ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(key, "1");

        String result01 = stringStringValueOperations.get(key);
        Assertions.assertThat(result01).isEqualTo("1");
        log.info("result01: {}", result01);

        stringStringValueOperations.increment(key);

        String result02 = stringStringValueOperations.get(key);
        Assertions.assertThat(result02).isEqualTo("2");
        log.info("result02: {}", result02);
    }
}