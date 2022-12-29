package com.aaron.spellcheckertranslator.sct.domain;

import com.aaron.spellcheckertranslator.spellchecker.domain.ErrInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SpellCheckerTranslatorResponse {
    private String originalText;
    private String correctedText;
    List<ErrInfo> spellCheckErrInfo;
    private String translatedText;

}
