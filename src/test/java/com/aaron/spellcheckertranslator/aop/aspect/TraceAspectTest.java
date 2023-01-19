package com.aaron.spellcheckertranslator.aop.aspect;

import com.aaron.spellcheckertranslator.spellchecker.service.PusanSpellCheckerClientService;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.papago.domain.Language;
import com.aaron.spellcheckertranslator.translator.papago.service.PapagoTransClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("alp")
class TraceAspectTest {

    @Autowired
    private PusanSpellCheckerClientService spellCheckerClientService;

    @Autowired
    private PapagoTransClientService transClientService;
    
    @Test
    void trace_test() {
        spellCheckerClientService.spellCheck("안녕 하세요");
        transClientService.translate(TranslatorRequest.builder()
                .text("안녕하세요")
                .srcLang(Language.KOREAN.getLang())
                .tgtLang(Language.ENGLISH.getLang())
                .build());
    }
}