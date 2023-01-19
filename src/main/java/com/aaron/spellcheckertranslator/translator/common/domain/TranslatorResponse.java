package com.aaron.spellcheckertranslator.translator.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TranslatorResponse {
    private String originalText;
    private String translatedText;
    private String errorCode;
    private String errorMessage;

    @Builder
    public TranslatorResponse(String originalText, String translatedText) {
        this.originalText = originalText;
        this.translatedText = translatedText;
    }
}
