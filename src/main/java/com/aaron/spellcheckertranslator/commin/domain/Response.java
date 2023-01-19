package com.aaron.spellcheckertranslator.commin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {

    private T data;

    private int status = HttpStatus.OK.value();
    private int code = HttpStatus.OK.value();
    private String message = "SUCCESS";

    public Response(T body) {
        this.data = body;
    }
}
