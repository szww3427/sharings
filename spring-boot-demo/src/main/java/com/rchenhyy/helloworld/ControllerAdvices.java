package com.rchenhyy.helloworld;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/9/1
 */
@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler
    @ResponseBody
    public String onException(Exception e) {
        return e.getMessage();
    }

}
