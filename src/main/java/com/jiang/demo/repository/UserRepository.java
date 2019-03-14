package com.jiang.demo.repository;


import com.jiang.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 继承JpaRepository来完成对数据库表的操作
 */
public interface UserRepository extends JpaRepository<User,Integer> {

}



