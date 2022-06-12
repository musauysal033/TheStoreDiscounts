package com.example.api;

import com.example.common.dto.ErrorMessage;
import com.example.common.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.example.api.controller")
public class GlobalControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorMessage notFoundExceptionHandler(NotFoundException ex)
    {
        log.error(ex.getMessage(), ex);
        return new ErrorMessage(String.valueOf(HttpStatus.NOT_FOUND.value())
                ,HttpStatus.NOT_FOUND.getReasonPhrase()
                ,ex.getMessage());
    }
}
