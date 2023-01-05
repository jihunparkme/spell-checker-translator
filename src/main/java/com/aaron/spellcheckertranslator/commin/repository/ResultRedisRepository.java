package com.aaron.spellcheckertranslator.commin.repository;

import com.aaron.spellcheckertranslator.commin.domain.ResultHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRedisRepository extends JpaRepository<ResultHistory, String> {
    Optional<List<ResultHistory>> findByIp(String ip);
}