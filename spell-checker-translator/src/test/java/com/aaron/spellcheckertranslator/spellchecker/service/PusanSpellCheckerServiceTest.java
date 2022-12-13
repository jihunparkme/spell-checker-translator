package com.aaron.spellcheckertranslator.spellchecker.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ActiveProfiles("prd")
class PusanSpellCheckerServiceTest {

    @Autowired
    private PusanSpellCheckerService spellCheckerService;

    @Test
    void spellCheck1() {
        String result = spellCheckerService.spellCheck("아빠가방에들어가십니다.");
        assertThat(result).isEqualTo("아빠가 방에 들어가십니다");
    }

    @Test
    void spellCheck2() {
        String result = spellCheckerService.spellCheck("오후부터는 눈이 시작되면서 퇴근길 시민들도 빙판길이나 차량 정체를 우려해 일찍부터 발길을 서두르는 모습이었습니다.");
        assertThat(result).isEqualTo("오후부터는 눈이 시작되면서 퇴근길 시민들도 빙판길이나 차량 정체를 우려해 일찍부터 발길을 서두르는 모습이었습니다.");
    }

    @Test
    void spellCheck3() {
        String result = spellCheckerService.spellCheck("내이름은아론입니다");
        assertThat(result).isEqualTo("내 이름은 아론입니다");
    }

    @Test
    void spellCheck4() {
        String result = spellCheckerService.spellCheck("내 이름은 아론입니다...");
        assertThat(result).isEqualTo("내 이름은 아론입니다...");
        log.info(result);
    }

    @Test
    void method() throws Exception {
        String a = "내 이름은 아론입니다|내이들은 아론입니다|내이는 아론입니다";
        String[] split = a.split("\\|");
    }
}