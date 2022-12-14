package com.aaron.spellcheckertranslator.spellchecker.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class SpellCheckerResponse {
    private String originalText;
    private String checkedText;
}
