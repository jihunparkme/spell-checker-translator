package com.aaron.spellcheckertranslator.spellchecker.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrInfo {
    private String help;
    private int errorIdx;
    private int correctMethod;
    private int start;
    private String errMsg;
    private int end;
    private String orgStr;
    private String candWord;
}
