package com.onwl007.apiblog.domain;

import com.google.gson.Gson;
import com.onwl007.apiblog.util.CodeMap;

/**
 * @author onwl007@126.com
 * @date 2018/4/26 21:03
 * @desc 返回统一封装的API数据
 */
public class RestResult {
    private int code;
    private Boolean success;
    private String message;
    private Object data;


    public RestResult setCodeMap(CodeMap codeMap){
        this.code=codeMap.getCode();
        this.message=codeMap.getMessage();
        return this;
    }

    public RestResult setCode(CodeMap codeMap){
        this.code=codeMap.getCode();
        return this;
    }

    public RestResult setMessage(String message){
        this.message=message;
        return this;
    }

    public RestResult setData(Object data){
        this.data=data;
        return this;
    }

    public RestResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public int getCode(){
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
