package com.aaron.spellcheckertranslator.spellchecker.service;

import com.aaron.spellcheckertranslator.spellchecker.domain.ErrInfo;
import com.aaron.spellcheckertranslator.spellchecker.domain.PusanResult;
import com.aaron.spellcheckertranslator.spellchecker.domain.SpellCheckerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PusanSpellCheckerService implements SpellCheckerService {

    private final PusanSpellCheckerApiService pusanSpellCheckerApiService;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public SpellCheckerResponse spellCheck(String text) {

        String[] separatedLines = text.split("\n");
        int size = separatedLines.length;

        StringBuffer sb = new StringBuffer();
        List<ErrInfo> errInfo = new ArrayList<>();
        for (int idx = 0; idx < size; idx++) {
            String line = separatedLines[idx];
            SpellCheckerResponse spellCheckerResponse = spellCheckByLine(line);
            sb.append(spellCheckerResponse.getCorrectedText());
            if (isInnerLine(size, idx)) {
                sb.append("\n");
            }
            errInfo.addAll(spellCheckerResponse.getErrInfo());
        }

        return SpellCheckerResponse.builder()
                .originalText(text)
                .correctedText(sb.toString())
                .errInfo(errInfo)
                .build();
    }

    private boolean isInnerLine(int size, int idx) {
        return idx < size - 1;
    }

    private SpellCheckerResponse spellCheckByLine(String text) {
        String response = pusanSpellCheckerApiService.spellCheck(text);
        if (StringUtils.isBlank(response)) {
            return SpellCheckerResponse.getDefaultResponse(text);
        }
        PusanResult resultObject = getResultObject(response);
        if (resultObject == null) {
            return SpellCheckerResponse.getDefaultResponse(text);
        }

        List<ErrInfo> errInfo = resultObject.getErrInfo();
        for (ErrInfo info : errInfo) {
            String checkedSpell = info.getCandWord().split("\\|")[0];
            text = text.replace(info.getOrgStr(), checkedSpell);
        }

        return SpellCheckerResponse.builder()
                .originalText(resultObject.getStr())
                .correctedText(text)
                .errInfo(errInfo)
                .build();
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
