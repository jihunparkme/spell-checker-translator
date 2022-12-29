package com.aaron.spellcheckertranslator.spellchecker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.time.Duration;

@Component
public class PusanSpellCheckerWebClientConfig {

    @Value("${spell.checker.pusan.url}")
    private String url;

    public HttpClient createHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public String getApiUrl() {
        return this.url;
    }
}
