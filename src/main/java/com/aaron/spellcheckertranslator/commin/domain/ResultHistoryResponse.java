package com.aaron.spellcheckertranslator.commin.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResultHistoryResponse {
    private List<ResultHistory> data;
}
