package com.jiang.demo.repository;

import com.jiang.demo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: 江云飞
 * Date:   2019/4/11
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {

    UserInfo  findByUsernameAndPassword(String username,String password);
    UserInfo findByUsername(String username);

}
