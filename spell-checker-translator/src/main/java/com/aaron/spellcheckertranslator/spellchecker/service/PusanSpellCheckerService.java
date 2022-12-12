package com.aaron.spellcheckertranslator.spellchecker.service;

import com.aaron.spellcheckertranslator.spellchecker.api.PusanSpellCheckerApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PusanSpellCheckerService implements SpellCheckerService {

    private final PusanSpellCheckerApiClient spellCheckerApiClient;

    @Override
    public String spellCheck(String text) {
        String result = spellCheckerApiClient.call(text);
        return result;
    }
}
