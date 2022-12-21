package com.aaron.spellcheckertranslator.translator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleTransApiService implements TranslatorApiService {

    private final GoogleTransClientService clientService;

    @Override
    public String translate(String text, String sourceLanguage, String targetLanguage) {
        return clientService.translate(text, sourceLanguage, targetLanguage);
    }
}
