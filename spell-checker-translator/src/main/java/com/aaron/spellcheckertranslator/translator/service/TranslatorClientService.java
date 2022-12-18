package com.aaron.spellcheckertranslator.translator.service;

import java.util.List;

public interface TranslatorClientService {
    List<String> translate(String text, String toLanguage);
}
