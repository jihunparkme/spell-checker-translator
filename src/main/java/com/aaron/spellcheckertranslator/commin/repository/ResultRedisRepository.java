package com.aaron.spellcheckertranslator.commin.repository;

import com.aaron.spellcheckertranslator.commin.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRedisRepository extends JpaRepository<Result, String> {
    Optional<List<Result>> findByIp(String ip);
}