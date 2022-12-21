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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
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
        String text = "'트위터 경영에서 물러나야 할까?' 일론 머스크 테슬라 최고경영자(CEO)가 던진 찬반 투표에 응답자 과반이 찬성표를 던지는 사상 초유의 사태가 벌어졌다. 머스크의 기이한 경영 행보에 한동안 곤두박질쳤던 테슬라 주가는 급등하며 이같은 소식을 반겼다. \"설문조사 결과를 따르겠다\"며 공언한 머스크가 실제로 경영 일선에서 물러설 것인지 시장의 관심이 쏠린다.";

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

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("status code: {}", response.statusCode());

        String body = response.body();
        log.info("result: {}", body);

        Pattern compile = Pattern.compile("(?<=\\[\")(.*?)(?=\\])");
        Matcher matcher = compile.matcher(body);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            List<String> strings = Arrays.asList(matcher.group(1).replace(",null", ""));
            for (String string : strings) {
                if (string.contains("[[")) {
                    String[] split = string.split(",\"");
                    for (String s : split) {
                        if (!s.contains("[[")) {
                            String replace = s.replace("\"", "").replace("\\", "\"");
                            log.info(replace);
                            sb.append(replace);
                        }
                    }
                }
            }
        }
        String result = sb.toString();
        assertThat(result).isEqualTo("'Should I step down from managing Twitter?' An unprecedented situation occurred in which the majority of respondents voted in favor of a vote cast by Tesla CEO Elon Musk. Tesla stock, which had plummeted for a while due to Musk's bizarre management moves, welcomed the news with a surge. The market's attention is focused on whether Musk, who announced that he will \"follow the survey results,\" will actually step down from the management front.");
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
