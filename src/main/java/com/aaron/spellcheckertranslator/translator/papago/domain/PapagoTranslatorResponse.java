package com.aaron.spellcheckertranslator.translator.papago.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PapagoTranslatorResponse {

    public static final PapagoTranslatorResponse EMPTY = new PapagoTranslatorResponse();

    private Message message;
    private String errorCode = "";
    private String errorMessage = "";

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static  class Message {

        private Result result;
    }
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Result {

        private String srcLangType;
        private String tarLangType;
        private String translatedText;
        private String engineType;
        private String pivot;
        private String dict;
        private String tarDict;
    }
    public String getTranslatedText() {
        return getMessage().getResult().getTranslatedText();
    }

    public boolean hasErrorCode() {
        return StringUtils.isBlank(this.errorCode);
    }
}
