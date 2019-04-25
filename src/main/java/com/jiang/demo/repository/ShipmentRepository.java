package com.jiang.demo.repository;


import com.jiang.demo.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public interface ShipmentRepository extends JpaRepository<Shipment,Integer>, JpaSpecificationExecutor<Shipment> {
}
