package com.aaron.spellcheckertranslator.translator.papago.controller;

import com.aaron.spellcheckertranslator.translator.google.domain.Language;
import com.aaron.spellcheckertranslator.translator.google.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.google.domain.TranslatorResponse;
import com.aaron.spellcheckertranslator.translator.papago.service.PapagoTransService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/translator")
public class PapagoTransController {

    private final PapagoTransService transService;

    @PostMapping("/papago")
    public TranslatorResponse papagoTranslator(TranslatorRequest request) {
        TranslatorResponse response = transService.translate(
                request.getText(),
                Language.from(request.getSrcLang()).getLang(),
                Language.from(request.getTgtLang()).getLang());

        log.info("REQUEST:: original: {}, result: {}", request.getText(), response.getTranslatedText());
        return TranslatorResponse.builder()
                .originalText(request.getText())
                .translatedText(response.getTranslatedText())
                .build();
    }
}
