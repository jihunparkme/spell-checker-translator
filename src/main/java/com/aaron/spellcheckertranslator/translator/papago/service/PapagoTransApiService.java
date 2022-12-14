package com.aaron.spellcheckertranslator.translator.papago.service;

import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.common.exception.TranslatorException;
import com.aaron.spellcheckertranslator.translator.common.service.TranslatorApiService;
import com.aaron.spellcheckertranslator.translator.papago.domain.TranslatorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Slf4j
@Service
@RequiredArgsConstructor
public class PapagoTransApiService implements TranslatorApiService {

    private final PapagoTransClientService clientService;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String translate(TranslatorRequest request) {
        String response = clientService.translate(
                TranslatorRequest.builder()
                        .text(getEncodedText(request.getText()))
                        .srcLang(request.getSrcLang())
                        .tgtLang(request.getTgtLang())
                        .build());
        TranslatorResponse responseObject = getResponseObject(response);
        return responseObject.getTranslatedText();
    }

    private String getEncodedText(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new TranslatorException("fail to encoding", e);
        }
    }

    private TranslatorResponse getResponseObject(String response) {
        try {
            return mapper.readValue(response, TranslatorResponse.class);
        } catch (JsonProcessingException e) {
            throw new TranslatorException("fail to json parsing", e);
        }
    }
}
