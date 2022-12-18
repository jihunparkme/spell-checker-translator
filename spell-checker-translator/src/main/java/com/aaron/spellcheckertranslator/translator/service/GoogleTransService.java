package com.aaron.spellcheckertranslator.translator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleTransService implements TranslatorService {

    private final GoogleTransApiService apiService;

    @Override
    public String translate(String text, String toLanguage) {
        String response = apiService.translate(text, toLanguage);
        return response;
    }

    /**
     * [[["hello. ","안녕하세요.",null,null,10],["My name is Aaron.","제 이름은 아론입니다.",null,null,3,null,null,[[]],[[["3377325d18169b98f25ba02b5cc513dc","ko_en_2022q2.md"]]]]],null,"ko",null,null,null,1,[],[["ko"],null,[1],["ko"]]]
     */
    //''.join([d[0] if d[0] else '' for d in data[0]])
    //"".join([caption[0] for caption in list(res.json())[0]])
}
