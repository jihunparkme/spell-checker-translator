package com.aaron.spellcheckertranslator.commin.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

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

        final String result01 = stringStringValueOperations.get(key);
        Assertions.assertThat(result01).isEqualTo("1");

        stringStringValueOperations.increment(key);

        final String result02 = stringStringValueOperations.get(key);
        Assertions.assertThat(result02).isEqualTo("2");
    }

    @Test
    public void list_test() {
        String key = "key01";
        ListOperations<String, String> stringStringListOperations = redisTemplate.opsForList();
        stringStringListOperations.rightPush(key, "H");
        stringStringListOperations.rightPush(key, "i");
        stringStringListOperations.rightPushAll(key, " ", "a", "a", "r", "o", "n");

        final String indexOfFirst = stringStringListOperations.index(key, 1);
        Assertions.assertThat(indexOfFirst).isEqualTo("i");

        final Long size = stringStringListOperations.size(key);
        Assertions.assertThat(size).isEqualTo(8);

        final List<String> resultString = stringStringListOperations.range(key, 0, 7);
        Assertions.assertThat(resultString).isEqualTo(Arrays.asList(new String[]{"H", "i", " ", "a", "a", "r", "o", "n"}));
    }
}