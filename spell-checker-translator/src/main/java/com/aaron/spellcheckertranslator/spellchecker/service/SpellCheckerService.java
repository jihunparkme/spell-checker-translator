package com.aaron.spellcheckertranslator.spellchecker.service;

import com.aaron.spellcheckertranslator.spellchecker.domain.SpellCheckerResponse;

public interface SpellCheckerService {
    SpellCheckerResponse spellCheck(String text);
}
