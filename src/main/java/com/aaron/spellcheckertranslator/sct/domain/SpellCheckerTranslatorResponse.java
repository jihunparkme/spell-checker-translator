package com.aaron.spellcheckertranslator.sct.domain;

import com.aaron.spellcheckertranslator.spellchecker.domain.ErrInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpellCheckerTranslatorResponse {
    private String originalText;
    private String correctedText;
    List<ErrInfo> spellCheckErrInfo;
    private String translatedText;
    private String translatorErrorCode;
    private String translatorErrorMessage;
}
