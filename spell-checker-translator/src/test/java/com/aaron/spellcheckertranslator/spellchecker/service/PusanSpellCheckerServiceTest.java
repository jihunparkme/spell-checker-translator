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
        assertThat(result).isEqualTo("아빠가 방에 들어가십니다.");
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
    }

    @Test
    void spellCheck5() {
        String result = spellCheckerService.spellCheck("대상 클래스만 다를뿐 로직은 유사하고, 대상 클래스 개수만큼 프록시 클래스 생성 필요");
        assertThat(result).isEqualTo("대상 클래스만 다를 뿐 로직은 유사하고, 대상 클래스 개수만큼 프락시 클래스 생성 필요");
    }

    @Test
    void string_replace_test() {
        String orgStr = "대상 클래스만 다를뿐 로직은 유사하고, 대상 클래스 개수만큼 프록시 클래스 생성 필요";
        String result = orgStr.replace("다를뿐", "다를 뿐");
        assertThat(result).isEqualTo("대상 클래스만 다를 뿐 로직은 유사하고, 대상 클래스 개수만큼 프록시 클래스 생성 필요");
    }
}