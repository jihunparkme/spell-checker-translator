package com.aaron.spellcheckertranslator.translator.common.service;

import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;

public interface TranslatorApiService {
    String translate(TranslatorRequest request);
}
