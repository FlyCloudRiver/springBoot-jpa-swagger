package com.jiang.demo.utils;

/**
 * Author: 江云飞
 * Date:   2019/4/17
 */
public class ResultUtil {

    @SuppressWarnings("unchecked")
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功！");
        result.setData(object);
        return result;
    }

    public static Result success(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功！");
        return result;
    }

    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return  result;
    }
}
