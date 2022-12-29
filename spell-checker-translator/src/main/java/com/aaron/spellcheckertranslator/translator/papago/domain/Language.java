package com.aaron.spellcheckertranslator.translator.papago.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Language {
    KOREAN("ko"),
    ENGLISH("en"),
    JAPANESE("ja"),
    CHINESE_SIMPLIFIED("zh-CN"),
    CHINESE_TRADITIONAL("zh-TW"),
    VIETNAMESE("vi"),
    INDONESIAN("id"),
    THAI("th"),
    GERMAN("de"),
    RUSSIAN("ru"),
    SPANISH("es"),
    ITALIAN("it"),
    FRENCH("fr"),
    ;

    private final String lang;

    public static Language from(String lang) {
        return Arrays.stream(Language.values())
                .filter(l -> l.lang.equals(lang))
                .findFirst()
                .orElse(KOREAN);
    }
}