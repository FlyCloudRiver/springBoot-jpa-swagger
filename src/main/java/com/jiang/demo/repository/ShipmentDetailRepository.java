package com.jiang.demo.repository;

import com.jiang.demo.entity.ShipmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/25
 */
public interface ShipmentDetailRepository extends JpaRepository<ShipmentDetail,Integer>, JpaSpecificationExecutor<ShipmentDetail> {

    List<ShipmentDetail> findByShipmentId(Integer shipmentId);
}
