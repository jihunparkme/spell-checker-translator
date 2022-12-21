package com.aaron.spellcheckertranslator.translator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleTransService implements TranslatorService {

    private final GoogleTransApiService apiService;

    @Override
    public String translate(String text, String toLanguage) {
        String response = apiService.translate(text, toLanguage);

        Pattern compile = Pattern.compile("(?<=\\[)(.*?)(?=\\])");
        Matcher matcher = compile.matcher(response);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            List<String> strings = Arrays.asList(matcher.group(1).replace(",null", ""));
            for (String string : strings) {
                if (string.contains("\",\"") && !string.contains(".md")) {
                    String[] split = string.split(",\"");
                    for (int i = 0; i < split.length; i++) {
                        if (i % 2 == 0) {
                            String replace = split[i].replace("[", "").replace("\"", "").replace("\\", "\"");
                            log.info(replace);
                            sb.append(replace);
                        }
                    }
                }
            }
        }
        String result = sb.toString();
        return result;
    }

    /**
     * [[["hello. ","안녕하세요.",null,null,10],["My name is Aaron.","제 이름은 아론입니다.",null,null,3,null,null,[[]],[[["3377325d18169b98f25ba02b5cc513dc","ko_en_2022q2.md"]]]]],null,"ko",null,null,null,1,[],[["ko"],null,[1],["ko"]]]
     */
    //''.join([d[0] if d[0] else '' for d in data[0]])
    //"".join([caption[0] for caption in list(res.json())[0]])
}
