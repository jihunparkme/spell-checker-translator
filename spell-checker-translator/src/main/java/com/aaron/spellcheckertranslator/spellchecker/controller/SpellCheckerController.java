package com.aaron.spellcheckertranslator.spellchecker.controller;

import com.aaron.spellcheckertranslator.spellchecker.domain.SpellCheckerRequest;
import com.aaron.spellcheckertranslator.spellchecker.domain.SpellCheckerResponse;
import com.aaron.spellcheckertranslator.spellchecker.service.PusanSpellCheckerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/spell/ckeck")
public class SpellCheckerController {

    private final PusanSpellCheckerService spellCheckerService;

    @PostMapping("/pusan")
    public SpellCheckerResponse pusanSpellCheck(SpellCheckerRequest request) {
        SpellCheckerResponse response = spellCheckerService.spellCheck(request.getText());
        return response;
    }
}
