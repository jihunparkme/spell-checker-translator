package com.aaron.spellcheckertranslator.commin.repository;

import com.aaron.spellcheckertranslator.commin.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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
    void save() throws Exception {
        // given
        Result result = Result.builder()
                .ip("127.0.0.1")
                .originalText("안녕하세요.")
                .translatedText("hello")
                .build();

        // when
        Result save = redisRepository.save(result);

        // then
        Result find = redisRepository.findById(save.getId()).get();
        log.info("id: {}", find.getId());
        log.info("original text: {}", find.getOriginalText());
        log.info("translated text: {}", find.getTranslatedText());

        Assertions.assertThat(save.getIp()).isEqualTo(find.getIp());
        Assertions.assertThat(save.getOriginalText()).isEqualTo(find.getOriginalText());
        Assertions.assertThat(save.getTranslatedText()).isEqualTo(find.getTranslatedText());
    }

    @Test
    void save_multi() throws Exception {
        // given
        Result rst1 = Result.builder()
                .ip("127.0.0.1")
                .originalText("안녕하세요.")
                .translatedText("hello")
                .build();

        Result rst2 = Result.builder()
                .ip("127.0.0.1")
                .originalText("반갑습니다.")
                .translatedText("Nice to meet you.")
                .build();

        Result rst3 = Result.builder()
                .ip("127.1.1.1")
                .originalText("반갑습니다.")
                .translatedText("Nice to meet you.")
                .build();

        // when
        redisRepository.save(rst1);
        redisRepository.save(rst2);
        redisRepository.save(rst3);

        // then
        List<Result> results = redisRepository.findByIp("127.0.0.1").get();
        Assertions.assertThat(results.size()).isEqualTo(2);
    }
}