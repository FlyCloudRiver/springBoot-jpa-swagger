package com.jiang.demo.repository;

import com.jiang.demo.entity.Storeroom;
import com.jiang.demo.entity.Tokens;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
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
    @Query(value = "select * from storeroom where goods_id=? order by update_time desc;", nativeQuery = true)
    List<Storeroom> selectInfo(Integer goodsId);


    //查询库存中所有的商品id集合
    @Query(value = "select goods_id from storeroom group by goods_id;", nativeQuery = true)
    List<Integer> findGoodsList();

    //查询数目
    //@Query(value = "select sum(s.number) from storeroom s  join goods g on s.goods_id=g.id where g.id=?1 and s.style=?2 and s.update_time>=?3 and s.update_time <=?4;", nativeQuery = true)
    @Query(value = "select sum(number) from storeroom  where goods_id=?1 and style=?2 and update_time>=?3 and update_time<=?4 ; ", nativeQuery = true)
    Integer findTotle(Integer id, String style, String startTime,String endTime);

    @Query(value = "select sum(number) from storeroom  where goods_id=?1 and style=?2 and update_time<=?3 ; ", nativeQuery = true)
    Integer findTotle2(Integer id, String style,String endTime);

    @Query(value = "select sum(number) from storeroom  where goods_id=?1 and style=?2 and update_time>=?3 ; ", nativeQuery = true)
    Integer findTotle3(Integer id, String style, String startTime);

    @Query(value = "select sum(number) from storeroom  where goods_id=?1 and style=?2 ; ", nativeQuery = true)
    Integer findTotle4(Integer id, String style);


    //查看某个商品的报表
    //@Query(value = "select s.* from storeroom s join goods g on s.goods_id=g.id where g.id=?1 and s.style=?2 and s.update_time>=?3 and s.update_time<=?4 ; ", nativeQuery = true)
    @Query(value = "select * from storeroom where goods_id=:id and style=:style and update_time>=:startTime and update_time<=:endTime ; ", nativeQuery = true)
    //List<Storeroom> findGoodsReport(Integer id, String style, Date startTime,Date endTime);
    List<Storeroom> findGoodsReport( @Param("id")Integer id,  @Param("style")String style,  @Param("startTime")String startTime, @Param("endTime")String endTime);


    @Query(value = "select * from storeroom where goods_id=:id and style=:style and update_time<=:endTime ; ", nativeQuery = true)
    List<Storeroom> findGoodsReport2( @Param("id")Integer id,  @Param("style")String style, @Param("endTime")String endTime);

    @Query(value = "select * from storeroom where goods_id=:id and style=:style and update_time>=:startTime ; ", nativeQuery = true)
        //List<Storeroom> findGoodsReport(Integer id, String style, Date startTime,Date endTime);
    List<Storeroom> findGoodsReport3( @Param("id")Integer id,  @Param("style")String style,  @Param("startTime")String startTime);

    @Query(value = "select * from storeroom where goods_id=:id and style=:style ; ", nativeQuery = true)
        //List<Storeroom> findGoodsReport(Integer id, String style, Date startTime,Date endTime);
    List<Storeroom> findGoodsReport4( @Param("id")Integer id,  @Param("style")String style);


}
