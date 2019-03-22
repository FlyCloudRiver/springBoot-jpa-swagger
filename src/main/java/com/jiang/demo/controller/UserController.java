package com.jiang.demo.controller;

import com.jiang.demo.entity.User;
import com.jiang.demo.repository.UserRepository;
import com.jiang.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;


@RestController
@Api(description = "用户" )   //swagger
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public User insertUser(User user){
        User save = userService.insertUser(user);
        return save;
    }

    @ApiOperation(value = "根据ID查询用户")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getuser(@PathVariable("id") Integer id){
        User user = userService.getuser(id);
        return user;
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User updateuser(User user){
        User user1 = userService.updateuser(user);
        return user1;
    }


    @ApiOperation(value = "获取用户列表 or（name模糊查询）")
    @RequestMapping(value = "/findByname", method = RequestMethod.GET)
    public List<User> findByName( String name,Integer pageSize,Integer pageNumber){
        /*pageSize每一页大小   pageNumber 第几页*/
        List<User> userlist = userService.findByName(name,pageSize,pageNumber);
        return userlist;
    }

    //@PathVariable指定URL变量名
    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login/", method = RequestMethod.POST)
    public User login( String name,String password){
        User user = userService.login(name,password);
        return user;
    }
}
