package com.aaron.spellcheckertranslator.sct.controller;

import com.aaron.spellcheckertranslator.sct.domain.ResultResponse;
import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorRequest;
import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorResponse;
import com.aaron.spellcheckertranslator.sct.service.SctServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/spl-ch-trnsl")
public class SctApiController {

    private final SctServiceImpl sctService;

    @PostMapping("/request-tr-gg")
    public SpellCheckerTranslatorResponse requestApplyGoogle(SpellCheckerTranslatorRequest request) {
        return sctService.spellCheckAndTranslatorApplyGoogle(request);
    }

    @PostMapping("/request-tr-pp")
    public SpellCheckerTranslatorResponse requestApplyPapago(SpellCheckerTranslatorRequest request) {
        return sctService.spellCheckAndTranslatorApplyPapago(request);
    }

    @GetMapping("/results")
    public ResultResponse getResults() {
        return sctService.getResults();
    }
}
