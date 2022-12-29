package com.aaron.spellcheckertranslator.translator.google.service;

public interface TranslatorClientService {
    String translate(String text, String sourceLanguage, String targetLanguage);
}
