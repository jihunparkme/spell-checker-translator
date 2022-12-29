package com.aaron.spellcheckertranslator.translator.google.service;

import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.common.service.TranslatorApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleTransApiService implements TranslatorApiService {

    private final GoogleTransClientService clientService;

    @Override
    public String translate(TranslatorRequest request) {
        return clientService.translate(request);
    }
}
