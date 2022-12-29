package com.aaron.spellcheckertranslator.sct.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SpellCheckerTranslatorRequest {
    private String text;
    private String srcLang;
    private String tgtLang;
}
