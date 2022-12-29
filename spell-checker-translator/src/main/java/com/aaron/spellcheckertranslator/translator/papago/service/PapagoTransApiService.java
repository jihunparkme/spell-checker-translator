package com.aaron.spellcheckertranslator.translator.papago.service;

import com.aaron.spellcheckertranslator.translator.common.service.TranslatorApiService;
import com.aaron.spellcheckertranslator.translator.papago.domain.TranslatorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class PapagoTransApiService implements TranslatorApiService {

    private final PapagoTransClientService clientService;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String translate(String text, String sourceLanguage, String targetLanguage) {
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("인코딩 실패", e);
        }

        String response = clientService.translate(text, sourceLanguage, targetLanguage);
        TranslatorResponse responseBody = TranslatorResponse.EMPTY;
        try {
            responseBody = mapper.readValue(response, TranslatorResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return responseBody.getTranslatedText();
    }
}
