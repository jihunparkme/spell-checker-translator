package com.aaron.spellcheckertranslator.api.spellchecker;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@ActiveProfiles("prd")
public class pusanApiTest {

    @Value("${spell.checker.pusan.url}")
    private String url;

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    @Test
    void properties_value_test() {
        assertTrue(StringUtils.isNotBlank(url));
        log.info("api url: {}", url);
    }

    @Test
    void pusan_spell_checker_api_request_test() throws Exception {

        Map<Object, Object> formData = new HashMap<>();
        formData.put("text1", "만나서반갑습니다. 내이름은아론입니다.");

        HttpRequest request = HttpRequest.newBuilder()
                .POST(ofFormData(formData))
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        log.info("status code: {}", response.statusCode());
    }

    public static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
}
