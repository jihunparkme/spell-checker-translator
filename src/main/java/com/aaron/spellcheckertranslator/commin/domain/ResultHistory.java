package com.aaron.spellcheckertranslator.commin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "resultHistory", timeToLive = 3600)
@AllArgsConstructor
@NoArgsConstructor
public class ResultHistory {

    @Id
    private String id;
    @Indexed
    private String ip;
    private String originalText;
    private String translatedText;
    @Indexed
    private LocalDateTime createDateTime;

    @Builder
    public ResultHistory(String ip, String originalText, String translatedText, LocalDateTime createDateTime) {
        this.ip = ip;
        this.originalText = originalText;
        this.translatedText = translatedText;
        this.createDateTime = createDateTime;
    }
}
