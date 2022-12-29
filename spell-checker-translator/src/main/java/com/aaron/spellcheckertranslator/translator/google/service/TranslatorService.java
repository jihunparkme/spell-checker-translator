package com.aaron.spellcheckertranslator.translator.google.service;

import com.aaron.spellcheckertranslator.translator.google.domain.TranslatorResponse;

public interface TranslatorService {
    TranslatorResponse translate(String text, String sourceLanguage, String targetLanguage);
}
