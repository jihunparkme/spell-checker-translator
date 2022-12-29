package com.aaron.spellcheckertranslator.translator.common.service;

public interface TranslatorClientService {
    String translate(String text, String sourceLanguage, String targetLanguage);
}
