package com.aaron.spellcheckertranslator.spellchecker.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * "str":"만나서반갑습니다. 내이름은아론입니다.",
 * "errInfo":[
 *      {
 *      "help":"조사나 어미 다음에 오는 말은 띄어 써야 합니다. (예) 아버지가방에 들어가신다.(×) -> 아버지가 방에 들어가신다.(○) 높고낮은 산봉우리.(×)-> 높고 낮은 산봉우리.(○) 아버지와어머니.(×)-> 아버지와 어머니.(○)",
 *      "errorIdx":0,
 *      "correctMethod":1,
 *      "start":0,
 *      "errMsg":"",
 *      "end":8,
 *      "orgStr":"만나서반갑습니다",
 *      "candWord":"만나서 반갑습니다"
 *      },
 *      {
 *      "help":"철자 검사를 해 보니 이 어절은 분석할 수 없으므로 틀린 말로 판단하였습니다.후보 어절은 이 철자검사/교정기에서 띄어쓰기, 붙여 쓰기, 음절대치와 같은 교정방법에 따라 수정한 결과입니다.후보 어절 중 선택하시거나 오류 어절을 수정하여 주십시오.* 단, 사전에 없는 단어이거나 사용자가 올바르다고 판단한 어절에 대해서는 통과하세요!!",
 *      "errorIdx":1,
 *      "correctMethod":1,
 *      "start":10,
 *      "errMsg":"",
 *      "end":19,
 *      "orgStr":"내이름은아론입니다",
 *      "candWord":"내 이름은 아론입니다|내이들은 아론입니다|내이는 아론입니다"
 *      }
 * ],
 * "idx":0
 */
@Getter
@Setter
@ToString
public class PusanResult {
    private String str;
    private List<ErrInfo> errInfo;
    private int idx;
}
