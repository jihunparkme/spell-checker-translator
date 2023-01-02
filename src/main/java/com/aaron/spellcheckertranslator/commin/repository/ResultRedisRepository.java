package com.aaron.spellcheckertranslator.commin.repository;

import com.aaron.spellcheckertranslator.commin.domain.Result;
import org.springframework.data.repository.CrudRepository;

public interface ResultRedisRepository extends CrudRepository<Result, String> {
}
