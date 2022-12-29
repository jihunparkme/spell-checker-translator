package com.aaron.spellcheckertranslator.translator.common.service;

import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorResponse;

public interface TranslatorService {
    TranslatorResponse translate(String text, String sourceLanguage, String targetLanguage);
}
