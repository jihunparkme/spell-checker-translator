package com.aaron.spellcheckertranslator.spellchecker.service;

import com.aaron.spellcheckertranslator.spellchecker.domain.ErrInfo;
import com.aaron.spellcheckertranslator.spellchecker.domain.PusanResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PusanSpellCheckerService implements SpellCheckerService {

    private final PusanSpellCheckerApiService pusanSpellCheckerApiService;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String spellCheck(String text) {
        String response = pusanSpellCheckerApiService.spellCheck(text);
        if (StringUtils.isBlank(response)) {
            return text;
        }
        PusanResult resultObject = getResultObject(response);
        if (resultObject == null) {
            return text;
        }

        StringBuffer result = new StringBuffer();
        List<ErrInfo> errInfo = resultObject.getErrInfo();
        errInfo.forEach(info -> {
            String checkedSpell = info.getCandWord().split("\\|")[0];
            result.append(checkedSpell);
        });

        return result.toString();
    }

    private PusanResult getResultObject(String response) {
        try {
            return mapper.readValue(response, PusanResult.class);
        } catch (Exception e) {
            log.info("fail to response mapper at pusan spell checker", e);
        }

        return null;
    }
}
