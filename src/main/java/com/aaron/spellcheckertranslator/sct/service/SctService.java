package com.aaron.spellcheckertranslator.sct.service;

import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorRequest;
import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorResponse;

public interface SctService {
    SpellCheckerTranslatorResponse spellCheckAndTranslatorApplyGoogle(SpellCheckerTranslatorRequest request);

    SpellCheckerTranslatorResponse spellCheckAndTranslatorApplyPapago(SpellCheckerTranslatorRequest request);
}
