package com.aaron.spellcheckertranslator.api.translator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
@ActiveProfiles("prd")
public class GoogleApiTest {

    @Value("${translator.google.apis.url}")
    private String url;

    private static final String ENGLISH = "en";
    private static final String KOREAN = "ko";
    private static final String JAPANESE = "ja";

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
    void google_translator_api_request_ko_en_test() throws Exception {
        String text = "만나서 반갑습니다. 내 이름은 아론입니다.";

        Map<Object, Object> formData = new HashMap<>();
        formData.put("client", "gtx");
        formData.put("sl", "auto");
        formData.put("dt", "t");
        formData.put("ie", "utf-8");
        formData.put("tl", ENGLISH);
        formData.put("q", text);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(ofFormData(formData))
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<Stream<String>> response = httpClient.send(request, HttpResponse.BodyHandlers.ofLines());
        log.info("status code: {}", response.statusCode());
        log.info("result: {}", response.body().collect(Collectors.toList()));
    }

    @Test
    void google_translator_api_request_en_ko_test() throws Exception {
        String text = "nice to see you. My name is Aaron.";

        Map<Object, Object> formData = new HashMap<>();
        formData.put("client", "gtx");
        formData.put("sl", "auto");
        formData.put("dt", "t");
        formData.put("ie", "utf-8");
        formData.put("tl", KOREAN);
        formData.put("q", text);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(ofFormData(formData))
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("status code: {}", response.statusCode());
        log.info("result: {}", response.body());
    }

    @Test
    void google_translator_api_request_ko_ja_test() throws Exception {
        String text = "만나서 반갑습니다. 내 이름은 아론입니다.";

        Map<Object, Object> formData = new HashMap<>();
        formData.put("client", "gtx");
        formData.put("sl", "auto");
        formData.put("dt", "t");
        formData.put("ie", "utf-8");
        formData.put("tl", JAPANESE);
        formData.put("q", text);

        HttpRequest request = HttpRequest.newBuilder()
                .POST(ofFormData(formData))
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("status code: {}", response.statusCode());
        log.info("result: {}", response.body());
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
