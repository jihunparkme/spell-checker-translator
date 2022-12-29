package com.aaron.spellcheckertranslator.translator.papago.service;

import com.aaron.spellcheckertranslator.translator.google.domain.Language;
import com.aaron.spellcheckertranslator.translator.google.domain.TranslatorResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ActiveProfiles("prd")
class PapagoTransServiceTest {

    @Autowired
    private PapagoTransService transService;

    @Test
    void translate01() throws Exception {
        String text = "문화예술로서 게임의 역할과 가능성에 대한 의견도 내놨다. 당장은 실용적으로 보이지 않을지 몰라도 아름다움을 통하거나 기대 이상의 집요한 노력 또는 새로운 기술과 시도가 주는 충격적인 경험을 만들어 낸다는 점에서 예술과 게임은 매우 많이 닮아 있다는 견해다.";
        TranslatorResponse translate = transService.translate(text, Language.KOREAN.getLang(), Language.ENGLISH.getLang());
        assertThat(translate.getTranslatedText()).isEqualTo("He also expressed opinions on the role and possibility of games as culture and art. Although it may not seem practical right now, art and games are very similar in that they create a shocking experience of persistent efforts or new technologies and attempts through beauty or beyond expectations.");
    }
}