package com.jiang.demo.repository;

import com.jiang.demo.entity.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/22
 */
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail,Integer>, JpaSpecificationExecutor<PurchaseDetail> {

    List<PurchaseDetail> findByPurchaseId(Integer purchaseId);
}
