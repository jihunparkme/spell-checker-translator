package com.aaron.spellcheckertranslator.translator.google.service;

public interface TranslatorApiService {
    String translate(String text, String sourceLanguage, String targetLanguage);
}
