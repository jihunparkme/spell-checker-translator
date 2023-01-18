package com.aaron.spellcheckertranslator.commin.repository;

import com.aaron.spellcheckertranslator.commin.domain.ResultHistory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@SpringBootTest
@ActiveProfiles("alp")
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
        ResultHistory result = ResultHistory.builder()
                .ip("127.0.0.1")
                .originalText("안녕하세요.")
                .translatedText("hello")
                .createDateTime(LocalDateTime.now())
                .build();

        // when
        ResultHistory save = redisRepository.save(result);

        // then
        ResultHistory find = redisRepository.findById(save.getId()).get();
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
        ResultHistory rst1 = ResultHistory.builder()
                .ip("127.0.0.1")
                .originalText("안녕하세요.")
                .translatedText("hello")
                .createDateTime(LocalDateTime.now())
                .build();

        ResultHistory rst2 = ResultHistory.builder()
                .ip("127.0.0.1")
                .originalText("반갑습니다.")
                .translatedText("Nice to meet you.")
                .createDateTime(LocalDateTime.now())
                .build();

        ResultHistory rst3 = ResultHistory.builder()
                .ip("127.1.1.1")
                .originalText("반갑습니다.")
                .translatedText("Nice to meet you.")
                .createDateTime(LocalDateTime.now())
                .build();

        // when
        redisRepository.save(rst1);
        redisRepository.save(rst2);
        redisRepository.save(rst3);

        // then
        List<ResultHistory> results = redisRepository.findByIpOrderByCreateDateTimeAsc("127.0.0.1").get();
        Assertions.assertThat(results.size()).isEqualTo(2);
    }

    @Test
    void search_order_by() throws Exception {
        // given
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ResultHistory rst1 = ResultHistory.builder()
                .ip("127.0.0.1")
                .originalText("안녕하세요.")
                .translatedText("hello")
                .createDateTime(LocalDateTime.parse("2023-01-05 20:20:20", dateTimeFormatter))
                .build();

        ResultHistory rst2 = ResultHistory.builder()
                .ip("127.0.0.1")
                .originalText("반갑습니다.")
                .translatedText("Nice to meet you.")
                .createDateTime(LocalDateTime.parse("2023-01-05 20:21:20", dateTimeFormatter))
                .build();

        ResultHistory rst3 = ResultHistory.builder()
                .ip("127.0.0.1")
                .originalText("반갑습니다.")
                .translatedText("Nice to meet you.")
                .createDateTime(LocalDateTime.parse("2023-01-05 20:22:20", dateTimeFormatter))
                .build();

        // when
        redisRepository.save(rst1);
        redisRepository.save(rst2);
        redisRepository.save(rst3);

        // then
        List<ResultHistory> results = redisRepository.findByIpOrderByCreateDateTimeAsc("127.0.0.1").get();
        Assertions.assertThat(results.get(0).getCreateDateTime()).isEqualTo(LocalDateTime.parse("2023-01-05 20:20:20", dateTimeFormatter));
    }
}