package com.aaron.spellcheckertranslator.commin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "result", timeToLive = 3600)
@AllArgsConstructor
public class Result {

    @Id
    private String id;
    private String originalText;
    private String translatedText;
}
