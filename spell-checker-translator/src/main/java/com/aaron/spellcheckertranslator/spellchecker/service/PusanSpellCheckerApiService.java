package com.aaron.spellcheckertranslator.spellchecker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PusanSpellCheckerApiService implements SpellCheckerApiService {

    private final PusanSpellCheckerClientService spellCheckerClientService;

    @Override
    public String spellCheck(String text) {
        return spellCheckerClientService.spellCheck(text);
    }
}
