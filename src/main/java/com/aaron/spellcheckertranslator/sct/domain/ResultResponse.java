package com.aaron.spellcheckertranslator.sct.domain;

import com.aaron.spellcheckertranslator.commin.domain.ResultHistory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResultResponse {
    List<ResultHistory> results;
}
