package com.aaron.spellcheckertranslator.translator.controller;

import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorRequest;
import com.aaron.spellcheckertranslator.translator.domain.Language;
import com.aaron.spellcheckertranslator.translator.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.domain.TranslatorResponse;
import com.aaron.spellcheckertranslator.translator.service.GoogleTransService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/translator")
public class GoogleTransController {

    private final GoogleTransService transService;

    @PostMapping("/google")
    public TranslatorResponse googleTranslator(TranslatorRequest request) {
        TranslatorResponse middleTranslate = transService.translate(
                request.getText(),
                Language.from(request.getSrcLang()).getLang(),
                Language.JAPANESE.getLang());
        TranslatorResponse finalTranslate = transService.translate(
                middleTranslate.getTranslatedText(),
                Language.JAPANESE.getLang(),
                Language.from(request.getTgtLang()).getLang());

        return TranslatorResponse.builder()
                .originalText(request.getText())
                .translatedText(finalTranslate.getTranslatedText())
                .build();
    }
}
