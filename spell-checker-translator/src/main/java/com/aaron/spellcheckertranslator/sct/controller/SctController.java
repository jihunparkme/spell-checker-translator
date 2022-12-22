package com.aaron.spellcheckertranslator.sct.controller;

import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorResponse;
import com.aaron.spellcheckertranslator.sct.service.SctServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/spl-ch-trnsl")
public class SctController {

    private final SctServiceImpl sctService;

    @PostMapping("/request")
    public SpellCheckerTranslatorResponse request(String text, String srcLang, String tgtLang) {
        return sctService.spellCheckAndTranslator(text, srcLang, tgtLang);
    }
}
