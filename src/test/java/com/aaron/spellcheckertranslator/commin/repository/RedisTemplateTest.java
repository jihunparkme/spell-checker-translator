package com.aaron.spellcheckertranslator.commin.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Arrays;
import java.util.Set;

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

    @Test
    public void set_test() {
        String key = "key01";

        SetOperations<String, String> stringStringSetOperations = redisTemplate.opsForSet();
        stringStringSetOperations.add(key, "H");
        stringStringSetOperations.add(key, "i");

        final Set<String> members = stringStringSetOperations.members(key);
        Assertions.assertThat(members.toArray()).isEqualTo(new String[]{"i", "H"});

        final Long size = stringStringSetOperations.size(key);
        Assertions.assertThat(size).isEqualTo(2);

        StringBuffer sb = new StringBuffer();
        final Cursor<String> cursor = stringStringSetOperations.scan(key, ScanOptions.scanOptions().match("*").count(3).build());
        while(cursor.hasNext()) {
            sb.append(cursor.next());
        }
        Assertions.assertThat(sb.toString()).isEqualTo("iH");
    }

    @Test
    public void sorted_set_test() {
        String key = "key01";

        ZSetOperations<String, String> stringStringZSetOperations = redisTemplate.opsForZSet();
        stringStringZSetOperations.add(key, "H", 1);
        stringStringZSetOperations.add(key, "i", 5);
        stringStringZSetOperations.add(key, "~", 10);
        stringStringZSetOperations.add(key, "!", 15);

        final Set<String> range = stringStringZSetOperations.range(key, 0, 3);
        Assertions.assertThat(range.toArray()).isEqualTo(new String[]{"H", "i", "~", "!"});

        final Long size = stringStringZSetOperations.size(key);
        Assertions.assertThat(size).isEqualTo(4);

        final Set<String> rangeByScore = stringStringZSetOperations.rangeByScore(key, 0, 15);
        Assertions.assertThat(rangeByScore.toArray()).isEqualTo(new String[]{"H", "i", "~", "!"});
    }
}