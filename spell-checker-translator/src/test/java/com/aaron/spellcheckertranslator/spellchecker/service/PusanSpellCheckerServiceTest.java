package com.aaron.spellcheckertranslator.spellchecker.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("prd")
class PusanSpellCheckerServiceTest {

    @Autowired
    private PusanSpellCheckerService spellCheckerService;

    @Test
    void spellCheck() throws Exception {
        String result = spellCheckerService.spellCheck("아빠가방에들어가십니다.");
        log.info("result: {}", result);
    }
}