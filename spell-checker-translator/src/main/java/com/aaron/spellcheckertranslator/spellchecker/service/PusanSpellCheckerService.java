package com.aaron.spellcheckertranslator.spellchecker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PusanSpellCheckerService implements SpellCheckerService {

    private final PusanSpellCheckerApiService pusanSpellCheckerApiService;

    @Override
    public String spellCheck(String text) {
        String response = pusanSpellCheckerApiService.spellCheck(text);

        String result = response;

        return result;
    }
}
