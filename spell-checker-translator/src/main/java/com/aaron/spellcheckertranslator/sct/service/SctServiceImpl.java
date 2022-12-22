package com.aaron.spellcheckertranslator.sct.service;

import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorResponse;
import com.aaron.spellcheckertranslator.spellchecker.domain.SpellCheckerResponse;
import com.aaron.spellcheckertranslator.spellchecker.service.PusanSpellCheckerService;
import com.aaron.spellcheckertranslator.translator.domain.Language;
import com.aaron.spellcheckertranslator.translator.domain.TranslatorResponse;
import com.aaron.spellcheckertranslator.translator.service.GoogleTransService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SctServiceImpl implements SctService {
    private final PusanSpellCheckerService spellCheckerService;
    private final GoogleTransService transService;

    public SpellCheckerTranslatorResponse spellCheckAndTranslator(String text, String srcLang, String tgtLang) {
        SpellCheckerResponse response = spellCheckerService.spellCheck(text);
        String correctedText = response.getCorrectedText();

        TranslatorResponse middleTranslate = transService.translate(
                correctedText,
                Language.from(srcLang).getLang(),
                Language.JAPANESE.getLang());
        TranslatorResponse finalTranslate = transService.translate(
                middleTranslate.getTranslatedText(),
                Language.JAPANESE.getLang(),
                Language.from(tgtLang).getLang());

        return SpellCheckerTranslatorResponse.builder()
                .originalText(text)
                .correctedText(correctedText)
                .spellCheckErrInfo(response.getErrInfo())
                .translatedText(finalTranslate.getTranslatedText())
                .build();
    }
}
