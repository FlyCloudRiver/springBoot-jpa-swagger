package com.jiang.demo.repository;


import com.jiang.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.util.List;

/**
 * 继承JpaRepository来完成对数据库表的操作
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    //nativeQuery = true  加了这个Query写原生态SQL语句

    /*根据名字模糊查询*/
    @Query(value = "select * from tbl_user u where u.user_name like %:name% limit :pagestart,:pagesize" ,nativeQuery = true)
    List<User> findByNameMatch(@Param("name") String name,@Param("pagestart") Integer a,@Param("pagesize") Integer b);

    /*分页查询*/
    @Query(value = "select * from tbl_user limit ?1,?2" ,nativeQuery = true)
    List<User> findAllPage(Integer a,Integer b) ;

    /*登录*/
    @Query(value = "select * from tbl_user u where u.user_name=?1 and u.user_password=?2",nativeQuery = true)
    User findByNameAndPassword(String name,String password);
}



