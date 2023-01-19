package com.aaron.spellcheckertranslator.translator.papago.service;

import com.aaron.spellcheckertranslator.aop.annotation.Trace;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.common.service.TranslatorService;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PapagoTransService implements TranslatorService {

    private final PapagoTransApiService apiService;

    @Override
    @Trace
    public TranslatorResponse translate(TranslatorRequest request) {
        TranslatorResponse translate = apiService.translate(request);
        log.info("REQUEST:: original: {}, result: {}", request.getText(), translate.getTranslatedText());
        return translate;
    }
}
