package com.jiang.demo.service;

import com.jiang.demo.entity.User;
import com.jiang.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /*添加用户*/
    public User insertUser(User user){
        User save = userRepository.save(user);
        return save;
    }

    /*根据ID查找用户*/
    public User getuser(@PathVariable("id") Integer id){
        User user = userRepository.findById(id).get();
        return user;
    }

  /*更新用户*/
    public User updateuser(User user){
        User user1 = userRepository.save(user);
        return user1;
    }

    /*获取用户列表*/
    private List<User> findAllUser(Integer a,Integer b){
        List<User> list=userRepository.findAllPage(a,b) ;
        return list;
    }

    /*根据名字查找用户*/
    public List<User> findByName(String name,Integer pageSize,Integer pageNumber){
        /*pageSize每一页大小   pageNumber 第几页*/
        /*int a=(pageSize*pageNumber)-pageSize;*/
        int a=pageSize*(pageNumber-1);
        int b=pageSize;

        List<User> list=new ArrayList<>();
        /*如果用户名为空*/
        if(name==null||name==""){
            System.out.println("执行没那么");
           list=this.findAllUser(a,b) ;
        }else{
            list = userRepository.findByNameMatch(name,a,b);
        }
        return list;
    }

    /*登录*/
    public User login( String name,String password){
        User user = userRepository.findByNameAndPassword(name,password);
        return user;
    }
}
