package com.aaron.spellcheckertranslator.translator.service;

import com.aaron.spellcheckertranslator.sct.util.RequestUtil;
import com.aaron.spellcheckertranslator.translator.config.GoogleTransWebClientConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleTransClientService implements TranslatorClientService {

    private final GoogleTransWebClientConfig webClientConfig;

    @Override
    public List<String> translate(String text, String toLanguage) {
        HttpClient httpClient = webClientConfig.createHttpClient();
        HttpRequest request = createHttpRequest(text, toLanguage);

        try {
            HttpResponse<Stream<String>> response = httpClient.send(request, HttpResponse.BodyHandlers.ofLines());
            if (HttpStatus.OK.equals(HttpStatus.valueOf(response.statusCode()))) {
                return response.body().collect(Collectors.toList());
            }
        } catch (Exception e) {
            log.error("fail to call pusan spell check api", e);
        }

        return Collections.EMPTY_LIST;
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
