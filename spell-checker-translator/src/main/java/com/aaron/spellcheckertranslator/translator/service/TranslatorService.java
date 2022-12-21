package com.aaron.spellcheckertranslator.translator.service;

import com.aaron.spellcheckertranslator.translator.domain.TranslatorResponse;

public interface TranslatorService {
    TranslatorResponse translate(String text, String sourceLanguage, String targetLanguage);
}
