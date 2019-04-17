package com.jiang.demo.exception;

/**
 * Author: 江云飞
 * Date:   2019/4/17
 */

/*自定义异常类型*/
public class MyException extends RuntimeException {
    private Integer code;

    public MyException(Integer code, String message){
        super(message);
        this.code=code;
    }

    public Integer getCode(){
        return  code;
    }

    public void setCode(Integer code){
        this.code=code;
    }
}
