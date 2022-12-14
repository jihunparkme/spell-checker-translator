package com.aaron.spellcheckertranslator.spellchecker.controller;

import com.aaron.spellcheckertranslator.spellchecker.service.PusanSpellCheckerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/spell/ckeck")
public class SpellCheckerController {

    private final PusanSpellCheckerService spellCheckerService;

    @GetMapping("/pusan")
    public String request(String text) {
        String result = spellCheckerService.spellCheck(text);
        log.info(result);
        return result;
    }
}
