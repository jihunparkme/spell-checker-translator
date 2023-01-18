package com.aaron.spellcheckertranslator.spellchecker.service;

import com.aaron.spellcheckertranslator.spellchecker.domain.SpellCheckerResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@ActiveProfiles("alp")
class PusanSpellCheckerServiceTest {

    @Autowired
    private PusanSpellCheckerService spellCheckerService;

    @Test
    void spellCheck1() {
        SpellCheckerResponse spellCheckerResponse = spellCheckerService.spellCheck("아빠가방에들어가십니다.");
        assertThat(spellCheckerResponse.getCorrectedText()).isEqualTo("아빠가 방에 들어가십니다.");
    }

    @Test
    void spellCheck2() {
        SpellCheckerResponse spellCheckerResponse = spellCheckerService.spellCheck("오후부터는 눈이 시작되면서 퇴근길 시민들도 빙판길이나 차량 정체를 우려해 일찍부터 발길을 서두르는 모습이었습니다.");
        assertThat(spellCheckerResponse.getCorrectedText()).isEqualTo("오후부터는 눈이 시작되면서 퇴근길 시민들도 빙판길이나 차량 정체를 우려해 일찍부터 발길을 서두르는 모습이었습니다.");
    }

    @Test
    void spellCheck3() {
        SpellCheckerResponse spellCheckerResponse = spellCheckerService.spellCheck("내이름은아론입니다");
        assertThat(spellCheckerResponse.getCorrectedText()).isEqualTo("내 이름은 아론입니다");
    }

    @Test
    void spellCheck3_1() {
        SpellCheckerResponse spellCheckerResponse = spellCheckerService.spellCheck("내 이름은 아론입니다.");
        assertThat(spellCheckerResponse.getCorrectedText()).isEqualTo("내 이름은 아론입니다.");
    }

    @Test
    void spellCheck4() {
        SpellCheckerResponse spellCheckerResponse = spellCheckerService.spellCheck("내 이름은 아론입니다...");
        assertThat(spellCheckerResponse.getCorrectedText()).isEqualTo("내 이름은 아론입니다….");
    }

    @Test
    void spellCheck5() {
        SpellCheckerResponse spellCheckerResponse = spellCheckerService.spellCheck("대상 클래스만 다를뿐 로직은 유사하고, 대상 클래스 개수만큼 프록시 클래스 생성 필요");
        assertThat(spellCheckerResponse.getCorrectedText()).isEqualTo("대상 클래스만 다를 뿐 로직은 유사하고, 대상 클래스 개수만큼 프락시 클래스 생성 필요");
    }

    @Test
    void spellCheck6() {
        String text = "안녕하세요!\n제 이름은말이죠\n무엇인지궁금하시죠?\n맞춰보세요";
        SpellCheckerResponse spellCheckerResponse = spellCheckerService.spellCheck(text);
        assertThat(spellCheckerResponse.getCorrectedText()).isEqualTo("안녕하세요!\n제 이름은 말이죠\n무엇인지 궁금하시죠?\n맞춰보세요.");
    }
}