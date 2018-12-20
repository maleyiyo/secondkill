package com.fish.controller;

import com.fish.error.BusinessException;
import com.fish.error.EmBusinessError;
import com.fish.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";
    //定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {

        Map<String, Object> responseDate = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseDate.put("errCode", businessException.getErrorCode());
            responseDate.put("errMsg", businessException.getErrMsg());
        } else {
            responseDate.put("errCode", EmBusinessError.UNKNOW_ERROR.getErrMsg());
            responseDate.put("errMsg", EmBusinessError.UNKNOW_ERROR.getErrorCode());
        }
        return CommonReturnType.create(responseDate, "fail");
    }
}
