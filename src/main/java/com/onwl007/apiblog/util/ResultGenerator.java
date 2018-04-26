package com.onwl007.apiblog.util;

import com.onwl007.apiblog.domain.RestResult;
import org.springframework.stereotype.Component;

/**
 * @author onwl007@126.com
 * @date 2018/4/26 21:56
 * @desc 生成json数据
 */
@Component
public class ResultGenerator {

    /**
     * 成功，但不返回数据,只返回请求结果
     * @return
     */
    public RestResult getSuccessResult(){
        return new RestResult()
                .setCodeMap(CodeMap.SUCCESS)
                .setSuccess(true);
    }

    /**
     * 成功，附带数据
     * @param data
     * @return
     */
    public RestResult getSuccessResult(Object data){
        return new RestResult()
                .setCodeMap(CodeMap.SUCCESS)
                .setSuccess(true)
                .setData(data);
    }

    /**
     * 成功，自定义消息及数据
     * @param message
     * @param data
     * @return
     */
    public RestResult getSuccessResult(String message,Object data){
        return new RestResult()
                .setCode(CodeMap.SUCCESS)
                .setSuccess(true)
                .setMessage(message)
                .setData(data);
    }

    /**
     * 失败，附带消息
     * @return
     */
    public RestResult getFailResult(){
        return new RestResult()
                .setSuccess(false)
                .setCodeMap(CodeMap.REQUEST_FAIL);
    }

    /**
     * 失败，自定义消息及数据
     * @param message
     * @param data
     * @return
     */
    public RestResult getFailResult(String message,Object data){
        return new RestResult()
                .setCode(CodeMap.REQUEST_FAIL)
                .setSuccess(false)
                .setMessage(message)
                .setData(data);
    }

    /**
     * 失败，CodeMap中其他失败状态码
     * @param code
     * @param data
     * @return
     */
    public RestResult getCodeMapFailResult(CodeMap code,Object data){
        return new RestResult()
                .setSuccess(false)
                .setCodeMap(code)
                .setData(data);
    }

    /**
     * 自定义状态码，消息及数据
     * @param code
     * @param message
     * @param data
     * @return
     */
    public RestResult getFreeResult(CodeMap code,String message,Object data){
        return new RestResult()
                .setCode(code)
                .setSuccess(false)
                .setMessage(message)
                .setData(data);
    }

}
