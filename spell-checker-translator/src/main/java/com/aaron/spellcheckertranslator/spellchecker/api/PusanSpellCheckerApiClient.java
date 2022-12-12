package com.aaron.spellcheckertranslator.spellchecker.api;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.*;

@Slf4j
@Component
public class PusanSpellCheckerApiClient {

    @Value("${spell.checker.pusan.url}")
    private String url;

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public String call(String text) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(ofFormData(getMapData(text)))
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (HttpStatus.OK.equals(HttpStatus.valueOf(response.statusCode()))) {
                return getResult(response);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return EMPTY;
    }

    private String getResult(HttpResponse<String> response) {
        Document doc = Jsoup.parse(response.body());
        return getResult(extractResultScript(doc.getElementsByTag("script")));
    }

    private String getResult(String script) {
        Pattern compile = Pattern.compile("(\\[)(.*?)(\\];)");
        Matcher matcher = compile.matcher(script);
        if (matcher.find()) {
            return matcher.group(2);
        }
        return EMPTY;
    }

    private String extractResultScript(Elements body) {
        if (body.get(2) != null) {
            return body.get(2).toString();
        }
        return EMPTY;
    }

    public static BodyPublisher ofFormData(Map<Object, Object> data) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return BodyPublishers.ofString(builder.toString());
    }

    private Map<Object, Object> getMapData(String text) {
        Map<Object, Object> formData = new HashMap<>();
        formData.put("text1", text);
        return formData;
    }
}
