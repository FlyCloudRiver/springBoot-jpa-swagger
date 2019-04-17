package com.jiang.demo.handle;

import com.jiang.demo.exception.MyException;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: 江云飞
 * Date:   2019/4/17
 */


/*捕获异常并处理*/
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof MyException){
            MyException myException = (MyException)e;
            return ResultUtil.error(myException.getCode(),myException.getMessage());
        }else{
            return  ResultUtil.error(-1,"错误！");
        }
    }
}
