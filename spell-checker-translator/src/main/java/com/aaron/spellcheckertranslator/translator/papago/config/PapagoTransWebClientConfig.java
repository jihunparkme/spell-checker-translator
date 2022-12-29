package com.aaron.spellcheckertranslator.translator.papago.config;

import com.aaron.spellcheckertranslator.translator.common.exception.TranslatorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class PapagoTransWebClientConfig {

    @Value("${translator.papago.url}")
    private String apiUrl;

    @Value("${translator.papago.client-id}")
    private String clientId;

    @Value("${translator.papago.client-secret}")
    private String clientSecret;

    public HttpURLConnection createHttpClient() {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new TranslatorException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new TranslatorException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    public Map<String, String> getRequestHeaders() {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        return requestHeaders;
    }
}
