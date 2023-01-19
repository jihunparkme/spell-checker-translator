package com.aaron.spellcheckertranslator.translator.common.service;

import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorResponse;

public interface TranslatorApiService {
    TranslatorResponse translate(TranslatorRequest request);
}
