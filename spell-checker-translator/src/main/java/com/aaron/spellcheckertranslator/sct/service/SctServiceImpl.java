package com.aaron.spellcheckertranslator.sct.service;

import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorRequest;
import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorResponse;
import com.aaron.spellcheckertranslator.spellchecker.domain.SpellCheckerResponse;
import com.aaron.spellcheckertranslator.spellchecker.service.PusanSpellCheckerService;
import com.aaron.spellcheckertranslator.translator.google.domain.Language;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorResponse;
import com.aaron.spellcheckertranslator.translator.google.service.GoogleTransService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SctServiceImpl implements SctService {
    private final PusanSpellCheckerService spellCheckerService;
    private final GoogleTransService transService;

    public SpellCheckerTranslatorResponse spellCheckAndTranslator(SpellCheckerTranslatorRequest request) {
        SpellCheckerResponse response = spellCheckerService.spellCheck(request.getText());
        String correctedText = response.getCorrectedText();

        TranslatorResponse finalTranslate = getTranslatedResponse(request, correctedText);

        log.info("REQUEST:: original: {}, result: {}",
                request.getText(), finalTranslate.getTranslatedText());
        return SpellCheckerTranslatorResponse.builder()
                .originalText(request.getText())
                .correctedText(correctedText)
                .spellCheckErrInfo(response.getErrInfo())
                .translatedText(finalTranslate.getTranslatedText())
                .build();
    }

    private TranslatorResponse getTranslatedResponse(SpellCheckerTranslatorRequest request, String correctedText) {
        TranslatorResponse middleTranslate = transService.translate(
                correctedText,
                Language.from(request.getSrcLang()).getLang(),
                Language.JAPANESE.getLang());
        TranslatorResponse finalTranslate = transService.translate(
                middleTranslate.getTranslatedText(),
                Language.JAPANESE.getLang(),
                Language.from(request.getTgtLang()).getLang());

        return finalTranslate;
    }
}
