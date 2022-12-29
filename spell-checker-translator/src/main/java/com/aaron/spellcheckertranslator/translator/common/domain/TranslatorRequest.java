package com.aaron.spellcheckertranslator.translator.common.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TranslatorRequest {
    private String text;
    private String srcLang;
    private String tgtLang;
}
