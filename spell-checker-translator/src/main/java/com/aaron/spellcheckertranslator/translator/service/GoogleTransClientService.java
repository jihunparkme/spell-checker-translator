package com.aaron.spellcheckertranslator.translator.service;

import com.aaron.spellcheckertranslator.sct.util.RequestUtil;
import com.aaron.spellcheckertranslator.translator.config.GoogleTransWebClientConfig;
import com.aaron.spellcheckertranslator.translator.domain.LANGUAGE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleTransClientService implements TranslatorClientService {

    private final GoogleTransWebClientConfig webClientConfig;

    @Override
    public String translate(String text, String toLanguage) {
        HttpClient httpClient = webClientConfig.createHttpClient();
        HttpRequest request = createHttpRequest(text, toLanguage);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (HttpStatus.OK.equals(HttpStatus.valueOf(response.statusCode()))) {
                return response.body();
            }
        } catch (Exception e) {
            log.error("fail to call pusan spell check api", e);
        }

        return EMPTY;
    }

    private HttpRequest createHttpRequest(String text, String toLanguage) {
        return HttpRequest.newBuilder()
                .POST(RequestUtil.ofFormData(getMapData(text, toLanguage)))
                .uri(URI.create(webClientConfig.getApiUrl()))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
    }

    private Map<Object, Object> getMapData(String text, String toLanguage) {
        Map<Object, Object> formData = new HashMap<>();
        formData.put("client", "gtx");
        formData.put("sl", "auto");
        formData.put("dt", "t");
        formData.put("ie", StandardCharsets.UTF_8.name());
        formData.put("tl", toLanguage);
        formData.put("q", text);
        return formData;
    }
}
