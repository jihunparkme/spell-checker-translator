package com.aaron.spellcheckertranslator.sct.service;

import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorResponse;

public interface SctService {
    SpellCheckerTranslatorResponse spellCheckAndTranslator(String text, String srcLang, String tgtLang);
}
