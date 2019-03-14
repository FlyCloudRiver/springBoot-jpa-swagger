package com.jiang.demo.controller;

import com.jiang.demo.entity.User;
import com.jiang.demo.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(description = "用户接口" )   //swagger
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;



    @ApiOperation(value = "添加用户")
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public User insertuser(User user){
        User save = userRepository.save(user);
        return save;
    }

    @ApiOperation(value = "根据ID查询用户")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getuser(@PathVariable("id") Integer id){
        User user = userRepository.findById(id).get();
        return user;
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User updateuser(User user){
        User user1 = userRepository.save(user);
        return user1;
    }

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> findAllUser(){
        List<User> list=userRepository.findAll() ;
        return list;
    }
}
