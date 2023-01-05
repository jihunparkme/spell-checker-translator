package com.aaron.spellcheckertranslator.commin.service;

import com.aaron.spellcheckertranslator.commin.domain.ResultHistoryResponse;
import com.aaron.spellcheckertranslator.commin.repository.ResultRedisRepository;
import com.aaron.spellcheckertranslator.sct.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisServiceImpl {

    private final ResultRedisRepository redisRepository;

    public ResultHistoryResponse searchResultHistory() {
        String clientIP = RequestUtil.getClientIP();
        return ResultHistoryResponse.builder()
                .data(redisRepository.findByIpOrderByCreateDateTimeDesc(clientIP).get())
                .build();
    }
}
