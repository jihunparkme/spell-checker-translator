package com.aaron.spellcheckertranslator.translator.service;

import java.util.List;

public interface TranslatorService {
    List<String> translate(String text, String toLanguage);
}
