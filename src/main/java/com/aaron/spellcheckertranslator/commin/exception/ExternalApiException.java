package com.aaron.spellcheckertranslator.commin.exception;

public class ExternalApiException extends RuntimeException {
    public ExternalApiException(String message, Exception e) {
        super(message, e);
    }
}
