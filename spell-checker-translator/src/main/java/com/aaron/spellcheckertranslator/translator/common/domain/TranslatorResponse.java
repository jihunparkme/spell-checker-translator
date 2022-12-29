package com.aaron.spellcheckertranslator.translator.common.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TranslatorResponse {
    private String originalText;
    private String translatedText;
}
