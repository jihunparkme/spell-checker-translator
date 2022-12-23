package com.aaron.spellcheckertranslator.translator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TranslatorRequest {
    private String text;
    private String srcLang;
    private String tgtLang;
}