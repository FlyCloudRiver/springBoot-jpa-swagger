package com.jiang.demo.controller;

import com.jiang.demo.dto.userInfo.UserInfoDTO;
import com.jiang.demo.entity.Tokens;
import com.jiang.demo.entity.UserInfo;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.permission.Login;
import com.jiang.demo.repository.TokenRepository;
import com.jiang.demo.service.UserInfoService;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: 江云飞
 * Date:   2019/4/11
 */

@RestController
@Api(description = "用户" )
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;


    @Autowired
    private TokenRepository tokenRepository;

    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public Result<UserInfoDTO> login(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam String username, @RequestParam String password) {

        //查询用户
        UserInfo byUsername = userInfoService.findByUsername(username, password);

        //判断登录
        try {
            //userService为自己定义的Service类
            if (byUsername != null) {
                /*用户id*/
                Integer uid = byUsername.getUid();
                List<Tokens> byuid = tokenRepository.findByuid(uid);
                Tokens save=null;
                //如果这个用户的token存在  就更新时间
                if(byuid.size()!=0){
                    Tokens tokens1 = byuid.get(0);
                    tokens1.setBuildtime(new Date());
                    save = tokenRepository.save(tokens1);
                }else{
                    /*token 信息*/
                    Tokens tokens = new Tokens();
                    tokens.setBuildtime(new Date());
                    tokens.setUserInfo(byUsername);
                    save = tokenRepository.save(tokens);
                }

                System.out.println("save===="+save);

                /*JSONObject object = JSONObject.fromObject(save);
                System.out.println("json==="+object);
                String jsonstr = object.toString();

                System.out.println("jsonstr=========="+jsonstr);*/
                /*Cookie cookie = new Cookie(byUsername.getUsername(), save.toString());*/
                Cookie cookie1= new Cookie("tookeId", save.getTokenid().toString());
                Cookie cookie2=new Cookie("username",byUsername.getUsername());
                System.out.println("cookie1-------"+cookie1.toString());
                System.out.println("cookie2-------"+cookie2.toString());
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                return ResultUtil.success(UserInfoDTO.convert(byUsername));
            } else
                throw new MyException(-1, "登陆失败");
        } catch (Exception e) {
            throw new MyException(-2, "未知错误");
        }

    }


   /* @PostMapping(value = "/isLogin")
    public Boolean isLogin(HttpServletRequest request) {
        System.out.println("4444444444444444444444444444444444444444444444444444444444444444444");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {

                    char[] chars = cookie.getValue().toCharArray();
                    System.out.println("cookie的value(Controller)="+chars);
                }
                return true;
            }else{
                return false;
            }

    }*/
}
