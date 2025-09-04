package com.example.classicimmortalserver.exception;

import com.example.classicimmortalserver.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//全局异常处理
@ControllerAdvice("com.example.classicimmortalserver.controller")
public class GlobalaExceptionHandler {


    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result error(CustomException e){
        return Result.error(e.getCode(), e.getMsg());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleOthers(Exception e) {
        // 可以打印堆栈，方便调试
        e.printStackTrace();
        return Result.error("500", "服务器内部错误");
    }
}
