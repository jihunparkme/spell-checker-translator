package com.aaron.spellcheckertranslator.translator.service;

public interface TranslatorApiService {
    String translate(String text, String sourceLanguage, String targetLanguage);
}
