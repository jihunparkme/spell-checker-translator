package com.aaron.spellcheckertranslator.spellchecker.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
@Builder
public class SpellCheckerResponse {
    private String originalText;
    private String correctedText;
    List<ErrInfo> errInfo;

    public static SpellCheckerResponse getDefaultResponse(String originalText) {
        return SpellCheckerResponse.builder()
                .originalText(originalText)
                .correctedText(originalText)
                .errInfo(Collections.EMPTY_LIST)
                .build();
    }
}
