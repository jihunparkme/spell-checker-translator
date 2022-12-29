package com.aaron.spellcheckertranslator.translator.common.exception;

public class TranslatorException extends RuntimeException {
    public TranslatorException(String message, Exception e) {
        super(message, e);
    }
}
