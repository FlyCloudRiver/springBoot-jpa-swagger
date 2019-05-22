package com.jiang.demo.repository;

import com.jiang.demo.entity.Storeroom;
import com.jiang.demo.entity.Tokens;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/20
 */
public interface StoreroomRepository extends JpaRepository<Storeroom,Integer>, JpaSpecificationExecutor<Storeroom> {


    @Query(value = "select * from storeroom where goods_id=? order by id desc;", nativeQuery = true)
    List<Storeroom> findByGoodsId(Integer goodsId);

    @Query(value = "select * from storeroom group by goods_id ORDER BY goods_id DESC limit :pageNum,:pageSize", nativeQuery = true)
    List<Storeroom> selectAll(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize);

    @Query(value = "select count(*) from (select * from storeroom group by goods_id) a;", nativeQuery = true)
    Integer selectSize();

    //根据商品id查询交易记录
    @Query(value = "select * from storeroom where goods_id=? order by id desc;", nativeQuery = true)
    List<Storeroom> selectInfo(Integer goodsId);

}
