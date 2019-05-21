package com.jiang.demo.repository;


import com.jiang.demo.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface GoodsRepository extends JpaRepository<Goods,Integer> , JpaSpecificationExecutor<Goods> {

   /* @Query(value = "insert into goods (goods_name,goods_price,commodity_id) values (?1,?2,?3)",nativeQuery = true)
    @Transactional
    @Modifying*/
    /*Integer InsertGoods(String GoodsName,Float price,Long commodityId);*/
}
