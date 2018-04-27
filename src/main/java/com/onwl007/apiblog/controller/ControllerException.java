package com.onwl007.apiblog.controller;

import com.onwl007.apiblog.util.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author onwl007@126.com
 * @date 2018/4/27 22:47
 * @desc 全局异常处理器，从controller抛出的异常由它统一处理
 *       已更友好的方式返回
 */
@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String,Object> handlerServiceException(ServiceException e){
        Map<String,Object> errorMessage=new HashMap<>();
        errorMessage.put("code",e.getCode());
        errorMessage.put("desc",e.getDesc());
        return errorMessage;
    }

}
