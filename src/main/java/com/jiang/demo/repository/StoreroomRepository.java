package com.jiang.demo.repository;

import com.jiang.demo.entity.Storeroom;
import com.jiang.demo.entity.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/20
 */
public interface StoreroomRepository extends JpaRepository<Storeroom,Integer>, JpaSpecificationExecutor<Storeroom> {


    @Query(value = "select * from storeroom where goods_id=? order by id desc;", nativeQuery = true)
    List<Storeroom> findByGoodsId(Integer goodsId);

    @Query(value = "select * from storeroom group by goods_id ORDER BY goods_id DESC;", nativeQuery = true)
    List<Storeroom> selectAll();
}
