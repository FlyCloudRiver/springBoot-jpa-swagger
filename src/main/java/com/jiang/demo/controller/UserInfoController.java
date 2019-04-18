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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
                //如果这个用户的token存在  就更新时间 更新密匙
                if(byuid.size()!=0){
                    Tokens tokens1 = byuid.get(0);
                    tokens1.setBuildtime(new Date());
                    tokens1.setToken(getItemID(5));
                    /*System.out.println("随机数"+getItemID(10));*/
                    save = tokenRepository.save(tokens1);
                }else{
                    /*token 信息*/
                    Tokens tokens = new Tokens();
                    tokens.setToken(getItemID(10));
                    tokens.setBuildtime(new Date());
                    tokens.setUserInfo(byUsername);
                    save = tokenRepository.save(tokens);
                }

                System.out.println("save===="+save);

                Cookie cookie1= new Cookie("tookeId", save.getTokenid().toString());
                Cookie cookie2=new Cookie("username",byUsername.getUsername());
                Cookie cookie3=new Cookie("token",save.getToken());

                response.addCookie(cookie1);
                response.addCookie(cookie2);
                response.addCookie(cookie3);
                return ResultUtil.success(UserInfoDTO.convert(byUsername));
            } else
                throw new MyException(-6, "用户名或密码错误");
        } catch (Exception e) {
            throw new MyException(-2, "未知错误");
        }

    }


    static int  num=0;
    /*生成随机的数字字母组合*/
    private static String getItemID( int n )
    {
        String val = "";
        Random random = new Random();
        for ( int i = 0; i < n; i++ )
        {
            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
            if ( "char".equalsIgnoreCase( str ) )
            { // 产生字母
                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) ( nextInt + random.nextInt( 26 ) );
            }
            else if ( "num".equalsIgnoreCase( str ) )
            { // 产生数字
                val += String.valueOf( random.nextInt( 10 ) );
            }
        }
        num=num+1;
        return val+num;
    }
}
