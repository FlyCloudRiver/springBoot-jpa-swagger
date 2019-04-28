package com.jiang.demo.service;


import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.entity.Storeroom;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: 江云飞
 * Date:   2019/4/20
 */
public interface StoreroomService {

    List<Storeroom> updateStoreroom(Map<Integer,Integer> map, Date time, String lastPerson);
}
