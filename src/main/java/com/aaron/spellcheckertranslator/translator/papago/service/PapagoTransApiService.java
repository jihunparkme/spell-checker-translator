package com.aaron.spellcheckertranslator.translator.papago.service;

import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorResponse;
import com.aaron.spellcheckertranslator.translator.common.exception.TranslatorException;
import com.aaron.spellcheckertranslator.translator.common.service.TranslatorApiService;
import com.aaron.spellcheckertranslator.translator.papago.domain.PapagoTranslatorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public TranslatorResponse translate(TranslatorRequest request) {
        String response = clientService.translate(
                TranslatorRequest.builder()
                        .text(getEncodedText(request.getText()))
                        .srcLang(request.getSrcLang())
                        .tgtLang(request.getTgtLang())
                        .build());
        PapagoTranslatorResponse papagoResponse = getResponseObject(response);
        if (papagoResponse.hasErrorCode()) {
            return TranslatorResponse.builder()
                    .originalText(request.getText())
                    .translatedText(StringUtils.EMPTY)
                    .errorCode(papagoResponse.getErrorCode())
                    .errorMessage(papagoResponse.getErrorMessage())
                    .build();
        }

        return TranslatorResponse.builder()
                .originalText(request.getText())
                .translatedText(papagoResponse.getTranslatedText())
                .build();
    }

    private String getEncodedText(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new TranslatorException("fail to encoding", e);
        }
    }

    private PapagoTranslatorResponse getResponseObject(String response) {
        try {
            return mapper.readValue(response, PapagoTranslatorResponse.class);
        } catch (JsonProcessingException e) {
            throw new TranslatorException("fail to json parsing", e);
        }
    }
}
