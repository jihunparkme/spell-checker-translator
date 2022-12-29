package com.aaron.spellcheckertranslator.translator.papago.service;

import com.aaron.spellcheckertranslator.translator.common.service.TranslatorClientService;
import com.aaron.spellcheckertranslator.translator.papago.config.PapagoTransWebClientConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PapagoTransClientService implements TranslatorClientService {

    private final PapagoTransWebClientConfig webClientConfig;

    @Override
    public String translate(String text, String sourceLanguage, String targetLanguage) {
        HttpURLConnection con = webClientConfig.createHttpClient();
        String postParams = "source=" + sourceLanguage + "&target=" + targetLanguage + "&text=" + text;
        try {
            con.setRequestMethod("POST");
            Map<String, String> requestHeaders = webClientConfig.getRequestHeaders();
            for(Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream());
            } else {
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
