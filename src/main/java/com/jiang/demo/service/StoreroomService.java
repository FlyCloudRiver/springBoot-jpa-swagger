package com.jiang.demo.service;


import com.jiang.demo.dto.Storeroom.StoreroomDTO;
import com.jiang.demo.dto.Storeroom.StoreroomForm;
import com.jiang.demo.dto.purchase.PurchaseStorageFrom;
import com.jiang.demo.dto.shipment.ShipmentStorageFrom;
import com.jiang.demo.utils.PageDTO;

import java.util.List;


/**
 * Author: 江云飞
 * Date:   2019/4/20
 */
public interface StoreroomService {

   // List<Storeroom> updateStoreroom(Map<Integer,Integer> map, Date time, String lastPerson);

    PageDTO<StoreroomDTO> select(StoreroomForm storeroomForm);
    //List<Storeroom> insertStoreroom (Map<Integer,Integer> map, Date time, String lastPerson);

    void insertStorage(PurchaseStorageFrom purchaseStorageFrom);

    void outputStorage(ShipmentStorageFrom shipmentStorageFrom);
    PageDTO<StoreroomDTO> selectAll(Integer pageNum,Integer pageSize);
}
