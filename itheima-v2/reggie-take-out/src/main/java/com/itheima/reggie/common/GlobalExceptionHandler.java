package com.itheima.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@ResponseBody
@ControllerAdvice(annotations = {Controller.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public BaseResponse<String> SQLIntegrityConstraintViolationExceptionHandler(
            SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
        log.error("error message: {}", sqlIntegrityConstraintViolationException.getMessage());
        if (sqlIntegrityConstraintViolationException.getMessage().contains("Duplicate entry")) {
            String[] spilt = sqlIntegrityConstraintViolationException.getMessage().split(" ");
            String msg = spilt[2] + "已存在";
            return BaseResponse.error(msg);
        }
        return BaseResponse.error("未知错误");
    }
}
