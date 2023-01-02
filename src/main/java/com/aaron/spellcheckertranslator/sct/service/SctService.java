package com.aaron.spellcheckertranslator.sct.service;

import com.aaron.spellcheckertranslator.sct.domain.ResultResponse;
import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorRequest;
import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface SctService {
    SpellCheckerTranslatorResponse spellCheckAndTranslatorApplyGoogle(SpellCheckerTranslatorRequest request, HttpServletRequest httpRequest);

    SpellCheckerTranslatorResponse spellCheckAndTranslatorApplyPapago(SpellCheckerTranslatorRequest request, HttpServletRequest httpRequest);

    ResultResponse getResults();
}
