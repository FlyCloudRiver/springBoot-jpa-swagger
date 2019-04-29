package com.jiang.demo.controller;

import com.jiang.demo.dto.userInfo.UserInfoDTO;
import com.jiang.demo.entity.Tokens;
import com.jiang.demo.entity.UserInfo;
import com.jiang.demo.exception.MyException;
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
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Author: 江云飞
 * Date:   2019/4/11
 */

@RestController
@Api(description = "用户登陆" )
@RequestMapping("/userInfo")
public class UserInfoController {
    // 通过set方法注入  优先选择
    private UserInfoService userInfoService;
    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    private TokenRepository tokenRepository;
    @Autowired
    public void setTokenRepository(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    @SuppressWarnings("unchecked")
    public Result<UserInfoDTO> login(HttpServletResponse response,
                                     @RequestParam String username, @RequestParam String password) {
        //查询用户
        UserInfo user = userInfoService.login(username, password);
        //判断登录
        try {
            //userService为自己定义的Service类
            if (user != null) {
                /*用户id*/
                Integer uid = user.getUid();
                List<Tokens> byUid = tokenRepository.findByUid(uid);
                Tokens save=new Tokens();
                //如果这个用户的token存在  就更新时间 更新密匙
                if(byUid.size()!=0){
                    Tokens tokens1 = byUid.get(0);
                    tokens1.setBuildtime(new Date());
                    String secretKey = isReplace(getItemID(10));
                    tokens1.setToken(secretKey);
                    //System.out.println("唯一随机数"+secretKey);
                    save = tokenRepository.save(tokens1);
                }else{
                    /*token 信息*/
                    Tokens tokens = new Tokens();
                    String secretKey = isReplace(getItemID(10));
                    tokens.setToken(secretKey);
                    tokens.setBuildtime(new Date());
                    tokens.setUserInfo(user);
                    save = tokenRepository.save(tokens);
                }

                System.out.println("save===="+save);

          /*      Cookie cookie1= new Cookie("tookeId", save.getTokenid().toString());*/
                /*Cookie cookie2=new Cookie("username",byUsername.getUsername());*/
                Cookie cookie=new Cookie("token",save.getToken());
                cookie.setPath("/");//可在同一应用服务器内共享cookie的方法
                cookie.setMaxAge(60*60*2);//设置cookie时间为两个小时

                response.addCookie(cookie);


                return ResultUtil.success(UserInfoDTO.convert(user));

            } else
                throw new MyException(-6, "用户名或密码错误");
        } catch (Exception e) {
            throw new MyException(-2, "未知错误");
        }

    }



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
            else
            { // 产生数字
                val += String.valueOf( random.nextInt( 10 ) );
            }
        }

        return val;
    }

    /*生成唯一随机数*/
    private String isReplace(String key){
          //去查询token  判断密匙是否存在 如果存在重新生成密匙  否则保存到数据库
           Integer token1 = tokenRepository.getToken(key);
           if(token1<1){
               return  key;
            }else{
               return isReplace(getItemID(5));
           }
    }
}
