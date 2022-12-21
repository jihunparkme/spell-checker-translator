package com.aaron.spellcheckertranslator.sct.controller;

import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorResponse;
import com.aaron.spellcheckertranslator.spellchecker.domain.SpellCheckerResponse;
import com.aaron.spellcheckertranslator.spellchecker.service.PusanSpellCheckerService;
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
@RequestMapping("/spl-ch-trnsl")
public class SctController {
    
    private final PusanSpellCheckerService spellCheckerService;
    private final GoogleTransService transService;
    

    @PostMapping("/request")
    public SpellCheckerTranslatorResponse pusanSpellCheck(String text, String sourceLanguage, String targetLanguage) {
        SpellCheckerResponse response = spellCheckerService.spellCheck(text);
        String correctedText = response.getCorrectedText();

        TranslatorResponse middleTranslate = transService.translate(correctedText, sourceLanguage, Language.JAPANESE.getLang());
        TranslatorResponse finalTranslate = transService.translate(middleTranslate.getTranslatedText(), Language.JAPANESE.getLang(), targetLanguage);

        return SpellCheckerTranslatorResponse.builder()
                .originalText(text)
                .correctedText(correctedText)
                .spellCheckErrInfo(response.getErrInfo())
                .translatedText(finalTranslate.getTranslatedText())
                .build();
    }
}
