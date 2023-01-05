package com.aaron.spellcheckertranslator.commin.controller;

import com.aaron.spellcheckertranslator.commin.domain.ResultHistory;
import com.aaron.spellcheckertranslator.commin.service.RedisServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisServiceImpl redisService;

    @PostMapping("/result/history")
    public List<ResultHistory> searchResultHistory() {
        return redisService.searchResultHistory();
    }
}
