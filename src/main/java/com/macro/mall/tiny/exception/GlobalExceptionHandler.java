package com.macro.mall.tiny.exception;

import com.alibaba.fastjson.JSONObject;
import com.macro.mall.tiny.common.api.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/8/5.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 缺省的错误处理器，所有特定错误无法处理的异常，均由此Handler处理.
     * 通过@ControllerAdvice+@ExceptionHandler进行全局的 Controller 层异常处理。
     * 只要设计得当，就再也不用在 Controller 层进行 try-catch 了
     * 具体异常信息返回可以通过枚举列举，通过异常e instanceOf 进行类型判断返回异常类别
     * 也设计一种默认的位置异常枚举，默认返回
     * @param ex
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object handleDefaultError(Exception ex) throws Exception {
        ex.printStackTrace();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", 500);
        result.put("message", "=,=系统异常，太尴尬了!"+ex.getMessage());
        if(ex instanceof CheckSubmitRepeatException){
            result.put("code", ResultCode.OPERAT_REPEAT.getCode());
            result.put("message", ResultCode.OPERAT_REPEAT.getMessage());
        }
        String errorStr= JSONObject.toJSONString(result);
        return errorStr;
    }

}
