package com.aaron.spellcheckertranslator.commin.service;

import com.aaron.spellcheckertranslator.commin.domain.ResultHistory;
import com.aaron.spellcheckertranslator.commin.repository.ResultRedisRepository;
import com.aaron.spellcheckertranslator.sct.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisServiceImpl {

    private ResultRedisRepository redisRepository;

    public List<ResultHistory> searchResultHistory() {
        String clientIP = RequestUtil.getClientIP();
        return redisRepository.findByIp(clientIP).get();
    }
}
