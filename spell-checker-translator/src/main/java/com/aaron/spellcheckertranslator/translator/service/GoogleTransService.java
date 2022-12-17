package com.aaron.spellcheckertranslator.translator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleTransService implements TranslatorService {

    private final GoogleTransApiService apiService;

    @Override
    public String translate(String text, String toLanguage) {
        String response = apiService.translate(text, toLanguage);
        return null;
    }
}
