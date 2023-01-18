package com.aaron.spellcheckertranslator.aop.aspect;

import com.aaron.spellcheckertranslator.spellchecker.service.PusanSpellCheckerService;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.papago.domain.Language;
import com.aaron.spellcheckertranslator.translator.papago.service.PapagoTransService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("alp")
class TraceAspectTest {

    @Autowired
    private PusanSpellCheckerService spellCheckerApiService;

    @Autowired
    private PapagoTransService transApiService;
    
    @Test
    void method() {
        spellCheckerApiService.spellCheck("안녕 하세요");
        transApiService.translate(TranslatorRequest.builder()
                .text("안녕하세요")
                .srcLang(Language.KOREAN.getLang())
                .tgtLang(Language.ENGLISH.getLang())
                .build());
    }
}