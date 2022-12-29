package com.aaron.spellcheckertranslator.sct.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SctController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
