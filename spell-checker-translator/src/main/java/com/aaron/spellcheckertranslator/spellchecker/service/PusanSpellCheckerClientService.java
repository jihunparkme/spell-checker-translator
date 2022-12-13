package com.aaron.spellcheckertranslator.spellchecker.service;

import com.aaron.spellcheckertranslator.spellchecker.config.PusanSpellCheckerWebClientConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Service
@RequiredArgsConstructor
public class PusanSpellCheckerClientService implements SpellCheckerClientService {

    private final PusanSpellCheckerWebClientConfig spellCheckerWebClientConfig;

    @Override
    public String spellCheck(String text) {
        HttpClient httpClient = spellCheckerWebClientConfig.createHttpClient();
        HttpRequest request = createHttpRequest(text);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (HttpStatus.OK.equals(HttpStatus.valueOf(response.statusCode()))) {
                return getResult(response);
            }
        } catch (Exception e) {
            log.error("fail to call pusan spell check api", e);
        }

        return EMPTY;
    }

    private HttpRequest createHttpRequest(String text) {
        return HttpRequest.newBuilder()
                .POST(ofFormData(getMapData(text)))
                .uri(URI.create(spellCheckerWebClientConfig.getApiUrl()))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
    }

    private Map<Object, Object> getMapData(String text) {
        Map<Object, Object> formData = new HashMap<>();
        formData.put("text1", text);
        return formData;
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

    private String getResult(HttpResponse<String> response) {
        Document doc = Jsoup.parse(response.body());
        return getResult(extractResultScript(doc.getElementsByTag("script")));
    }

    private String extractResultScript(Elements body) {
        if (body.get(2) != null) {
            return body.get(2).toString();
        }
        return EMPTY;
    }

    private String getResult(String script) {
        Pattern compile = Pattern.compile("(\\[)(.*?)(\\];)");
        Matcher matcher = compile.matcher(script);
        if (matcher.find()) {
            return matcher.group(2);
        }
        return EMPTY;
    }
}
