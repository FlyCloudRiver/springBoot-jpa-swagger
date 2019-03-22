package com.jiang.demo.repository;


import com.jiang.demo.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommodityRepository extends JpaRepository<Commodity,Integer> {

    /*添加厂商*/
    @Query(value = "insert into commodity (commodity_id,commodity_name) values (?1 , ?2)",nativeQuery = true)
    @Transactional
    @Modifying
    Integer saveCommodity(Long commodityId, String commodityName);

}
