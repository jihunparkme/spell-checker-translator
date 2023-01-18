package com.aaron.spellcheckertranslator.sct.service;

import com.aaron.spellcheckertranslator.commin.domain.ResultHistory;
import com.aaron.spellcheckertranslator.commin.repository.ResultRedisRepository;
import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorRequest;
import com.aaron.spellcheckertranslator.sct.domain.SpellCheckerTranslatorResponse;
import com.aaron.spellcheckertranslator.sct.util.RequestUtil;
import com.aaron.spellcheckertranslator.spellchecker.domain.SpellCheckerResponse;
import com.aaron.spellcheckertranslator.spellchecker.service.PusanSpellCheckerService;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorRequest;
import com.aaron.spellcheckertranslator.translator.google.domain.Language;
import com.aaron.spellcheckertranslator.translator.common.domain.TranslatorResponse;
import com.aaron.spellcheckertranslator.translator.google.service.GoogleTransService;
import com.aaron.spellcheckertranslator.translator.papago.service.PapagoTransService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SctServiceImpl implements SctService {

    private final PusanSpellCheckerService spellCheckerService;
    private final GoogleTransService googleTransService;
    private final PapagoTransService papagoTransService;
    private final ResultRedisRepository redisRepository;

    public SpellCheckerTranslatorResponse spellCheckAndTranslatorApplyGoogle(SpellCheckerTranslatorRequest request) {
        SpellCheckerResponse response = getSpellCheckerResponse(request);

        String correctedText = response.getCorrectedText();
        TranslatorResponse finalTranslate = getTranslatedResponse(request, correctedText);

        saveResult(request, finalTranslate);

        log.info("REQUEST:: original: {}, result: {}",
                request.getText(), finalTranslate.getTranslatedText());
        return SpellCheckerTranslatorResponse.builder()
                .originalText(request.getText())
                .correctedText(correctedText)
                .spellCheckErrInfo(response.getErrInfo())
                .translatedText(finalTranslate.getTranslatedText())
                .build();
    }

    private void saveResult(SpellCheckerTranslatorRequest request, TranslatorResponse finalTranslate) {
        String clientIP = RequestUtil.getClientIP();
        redisRepository.save(ResultHistory.builder()
                .ip(clientIP)
                .originalText(request.getText())
                .translatedText(finalTranslate.getTranslatedText())
                .createDateTime(LocalDateTime.now())
                .build());
    }

    @Override
    public SpellCheckerTranslatorResponse spellCheckAndTranslatorApplyPapago(SpellCheckerTranslatorRequest request) {
        SpellCheckerResponse response = getSpellCheckerResponse(request);

        String correctedText = response.getCorrectedText();
        TranslatorResponse finalTranslate = papagoTransService.translate(TranslatorRequest.builder()
                .text(correctedText)
                .srcLang(Language.from(request.getSrcLang()).getLang())
                .tgtLang(Language.from(request.getTgtLang()).getLang())
                .build());

        saveResult(request, finalTranslate);

        log.info("REQUEST:: original: {}, result: {}",
                request.getText(), finalTranslate.getTranslatedText());
        return SpellCheckerTranslatorResponse.builder()
                .originalText(request.getText())
                .correctedText(correctedText)
                .spellCheckErrInfo(response.getErrInfo())
                .translatedText(finalTranslate.getTranslatedText())
                .build();
    }

    private SpellCheckerResponse getSpellCheckerResponse(SpellCheckerTranslatorRequest request) {
        request.setText(request.getText().replace("\r\n", "\n"));
        SpellCheckerResponse response = spellCheckerService.spellCheck(request.getText());
        return response;
    }

    private TranslatorResponse getTranslatedResponse(SpellCheckerTranslatorRequest request, String correctedText) {
        TranslatorResponse middleTranslate = googleTransService.translate(
                TranslatorRequest.builder()
                        .text(request.getText())
                        .srcLang(Language.from(request.getSrcLang()).getLang())
                        .tgtLang(Language.JAPANESE.getLang())
                        .build());

        TranslatorResponse finalTranslate = googleTransService.translate(
                TranslatorRequest.builder()
                        .text(middleTranslate.getTranslatedText())
                        .srcLang(Language.JAPANESE.getLang())
                        .tgtLang(Language.from(request.getTgtLang()).getLang())
                        .build());

        return finalTranslate;
    }
}
