package com.aaron.spellcheckertranslator.translator.google.service;

import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.common.service.TranslatorService;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleTransService implements TranslatorService {

    private final GoogleTransApiService apiService;

    @Override
    public TranslatorResponse translate(TranslatorRequest request) {
        TranslatorResponse translateResponse = apiService.translate(request);

        String translatedText = extractTranslatedText(translateResponse.getTranslatedText());
        return TranslatorResponse.builder()
                .originalText(request.getText())
                .translatedText(translatedText)
                .build();
    }

    private String extractTranslatedText(String response) {
        Pattern pattern = Pattern.compile("(?<=\\[)(.*?)(?=\\])");
        Matcher matcher = pattern.matcher(response);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            List<String> matcherTextList = Arrays.asList(matcher.group(1).replace(",null", ""));
            for (String matcherText : matcherTextList) {
                if (isTranslatorArea(matcherText)) {
                    getTranslatedText(sb, matcherText);
                }
            }
        }

        return sb.toString().replace("\"n", "\n");
    }

    private void getTranslatedText(StringBuffer sb, String matcherText) {
        String[] split = matcherText.split(",\"");
        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 0) {
                sb.append(getTranslatedsentence(split[i]));
            }
        }
    }

    private String getTranslatedsentence(String split) {
        return split.replace("[", "").replace("\"", "").replace("\\", "\"");
    }

    private boolean isTranslatorArea(String matcherText) {
        return matcherText.contains("\",\"") && !matcherText.contains(".md");
    }
}
