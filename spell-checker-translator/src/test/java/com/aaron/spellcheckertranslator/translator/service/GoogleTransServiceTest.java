package com.aaron.spellcheckertranslator.translator.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.nio.charset.StandardCharsets;

@Slf4j
@SpringBootTest
@ActiveProfiles("prd")
class GoogleTransServiceTest {

    @Autowired
    private GoogleTransService transService;

    @Test
    void translate01() throws Exception {
        String response = transService.translate("안녕하세요. 제 이름은 아론입니다.", StandardCharsets.UTF_8.name());
        log.info("response: {}", response);
    }
}