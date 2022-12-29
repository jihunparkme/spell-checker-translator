package com.aaron.spellcheckertranslator.translator.common.service;

import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;

public interface TranslatorClientService {
    String translate(TranslatorRequest request);
}
