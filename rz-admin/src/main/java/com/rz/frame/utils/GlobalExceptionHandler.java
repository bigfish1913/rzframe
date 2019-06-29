package com.rz.frame.utils;

import com.rz.frame.model.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.rz.frame.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult errorResult(RuntimeException ex) {
        JsonResult result = new JsonResult();
        result.setCode(500);
        result.setMessage("系统出错");
        ex.printStackTrace();
        return result;
    }
}
