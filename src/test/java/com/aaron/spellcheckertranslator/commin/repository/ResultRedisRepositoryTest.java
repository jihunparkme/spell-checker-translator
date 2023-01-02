package com.aaron.spellcheckertranslator.commin.repository;

import com.aaron.spellcheckertranslator.commin.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("prd")
class ResultRedisRepositoryTest {

    @Autowired
    private ResultRedisRepository redisRepository;

    @AfterEach
    void afterAll() {
        redisRepository.deleteAll();
    }

    @Test
    void test() throws Exception {
        // given
        Result save = Result.builder()
                .ip("127.0.0.1")
                .originalText("안녕하세요.")
                .translatedText("hello")
                .build();

        // when
        Result hello = redisRepository.save(save);

        // then
        Result find = redisRepository.findById(hello.getId()).get();

        Assertions.assertThat(hello.getIp()).isEqualTo(find.getIp());
        Assertions.assertThat(hello.getOriginalText()).isEqualTo(find.getOriginalText());
        Assertions.assertThat(hello.getOriginalText()).isEqualTo(find.getOriginalText());
    }
}