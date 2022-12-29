package com.aaron.spellcheckertranslator.translator.papago.service;

import com.aaron.spellcheckertranslator.translator.common.service.TranslatorService;
import com.aaron.spellcheckertranslator.translator.google.domain.TranslatorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PapagoTransService implements TranslatorService {

    private final PapagoTransApiService apiService;

    @Override
    public TranslatorResponse translate(String text, String sourceLanguage, String targetLanguage) {
        String translatedText = apiService.translate(text, sourceLanguage, targetLanguage);
        return TranslatorResponse.builder()
                .originalText(text)
                .translatedText(translatedText)
                .build();
    }
}
