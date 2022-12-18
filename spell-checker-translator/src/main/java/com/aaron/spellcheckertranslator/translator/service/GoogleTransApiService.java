package com.aaron.spellcheckertranslator.translator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleTransApiService implements TranslatorApiService {

    private final GoogleTransClientService clientService;

    @Override
    public List<String> translate(String text, String toLanguage) {
        return clientService.translate(text, toLanguage);
    }
}
