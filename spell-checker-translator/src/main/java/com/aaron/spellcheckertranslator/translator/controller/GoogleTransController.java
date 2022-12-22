package com.aaron.spellcheckertranslator.translator.controller;

import com.aaron.spellcheckertranslator.translator.domain.Language;
import com.aaron.spellcheckertranslator.translator.domain.TranslatorResponse;
import com.aaron.spellcheckertranslator.translator.service.GoogleTransService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/translator")
public class GoogleTransController {

    private final GoogleTransService transService;

    @PostMapping("/google")
    public TranslatorResponse googleTranslator(String text, String srcLang, String tgtLang) {
        TranslatorResponse middleTranslate = transService.translate(
                text,
                Language.from(srcLang).getLang(),
                Language.JAPANESE.getLang());
        TranslatorResponse finalTranslate = transService.translate(
                middleTranslate.getTranslatedText(),
                Language.JAPANESE.getLang(),
                Language.from(tgtLang).getLang());

        return TranslatorResponse.builder()
                .originalText(text)
                .translatedText(finalTranslate.getTranslatedText())
                .build();
    }
}
