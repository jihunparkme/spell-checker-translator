package com.aaron.spellcheckertranslator.translator.google.service;

import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.google.domain.Language;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ActiveProfiles("alp")
class GoogleTransServiceTest {

    @Autowired
    private GoogleTransService transService;

    @Test
    void translate01() throws Exception {
        String text = "안녕하세요. 제 이름은 아론입니다.";
        TranslatorResponse translate = transService.translate(
                TranslatorRequest.builder()
                        .text(text)
                        .srcLang(Language.KOREAN.getLang())
                        .tgtLang(Language.ENGLISH.getLang())
                        .build());
        assertThat(translate.getTranslatedText()).isEqualTo("hello. My name is Aaron.");
    }

    @Test
    void translate02() throws Exception {
        String text = "'트위터 경영에서 물러나야 할까?' 일론 머스크 테슬라 최고경영자(CEO)가 던진 찬반 투표에 응답자 과반이 찬성표를 던지는 사상 초유의 사태가 벌어졌다. 머스크의 기이한 경영 행보에 한동안 곤두박질쳤던 테슬라 주가는 급등하며 이같은 소식을 반겼다. \"설문조사 결과를 따르겠다\"며 공언한 머스크가 실제로 경영 일선에서 물러설 것인지 시장의 관심이 쏠린다.";
        TranslatorResponse translate = transService.translate(
                TranslatorRequest.builder()
                        .text(text)
                        .srcLang(Language.KOREAN.getLang())
                        .tgtLang(Language.ENGLISH.getLang())
                        .build());
        assertThat(translate.getTranslatedText()).isEqualTo("'Should I step down from managing Twitter?' An unprecedented incident occurred in which the majority of respondents voted in favor of a vote cast by Tesla CEO Elon Musk. Tesla stock, which had plummeted for a while due to Musk's bizarre management moves, welcomed the news with a surge. The market's attention is focused on whether Musk, who announced that he will \"follow the survey results,\" will actually step down from the management front.");
    }

    @Test
    void translate03() throws Exception {
        String text = "문화예술로서 게임의 역할과 가능성에 대한 의견도 내놨다. 당장은 실용적으로 보이지 않을지 몰라도 아름다움을 통하거나 기대 이상의 집요한 노력 또는 새로운 기술과 시도가 주는 충격적인 경험을 만들어 낸다는 점에서 예술과 게임은 매우 많이 닮아 있다는 견해다.";
        TranslatorResponse translate = transService.translate(
                TranslatorRequest.builder()
                        .text(text)
                        .srcLang(Language.KOREAN.getLang())
                        .tgtLang(Language.ENGLISH.getLang())
                        .build());
        assertThat(translate.getTranslatedText()).isEqualTo("Opinions were also presented on the role and potential of games as a cultural art. It may not seem practical right away, but it is the view that art and games are very similar in that they create shocking experiences through beauty, persistent effort beyond expectations, or new technology and attempts.");
    }
}