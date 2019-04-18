package com.jiang.demo.repository;

import com.jiang.demo.entity.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Author: 江云飞
 * Date:   2019/4/18
 */

@Repository
public interface TokenRepository extends JpaRepository<Tokens,Integer> {

    //查询已完成
    @Query(value = "select * from tokens where user_info_uid=?1 order by tokenid desc;", nativeQuery = true)
    List<Tokens> findByuid(Integer id);

    @Query(value = "select s.role from user_info u,sys_role s,sys_user_role sur where u.uid=sur.uid and sur.role_id=s.id and username=?", nativeQuery = true)
    String getRoleName(String username);

    @Query(value = "select count(tokenid)  FROM tokens where token=?", nativeQuery = true)
    Integer getToken(String token);

}
