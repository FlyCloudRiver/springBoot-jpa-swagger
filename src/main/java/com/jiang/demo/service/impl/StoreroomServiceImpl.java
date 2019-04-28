package com.jiang.demo.service.impl;

import com.jiang.demo.dto.Storeroom.StoreroomForm;
import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailForm;
import com.jiang.demo.entity.Storeroom;
import com.jiang.demo.repository.StoreroomRepository;
import com.jiang.demo.service.StoreroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: 江云飞
 * Date:   2019/4/20
 */

@Service
public class StoreroomServiceImpl implements StoreroomService {

    private StoreroomRepository storeroomRepository;
    @Autowired
    public void setStoreroomRepository(StoreroomRepository storeroomRepository) {
        this.storeroomRepository = storeroomRepository;
    }


    /*更新库房*/
    public List<Storeroom> updateStoreroom(Map<Integer,Integer> map, Date time, String lastPerson){

        Lock lock = new ReentrantLock();

        List<Storeroom> storeroomList =new ArrayList<>();
        //map  key为goodsId   value为goods Number
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            //根据商品id查询库存  然后改变库存量  操作人  更新时间
            Storeroom storeroom = storeroomRepository.findByGoodsId(entry.getKey());
            //库存量
            Integer amount = storeroom.getAmount();
            //更新时间
            storeroom.setUpdateTime(time);
            //更新库存
            lock.lock();
            storeroom.setAmount(amount+Integer.valueOf(entry.getValue()));
            lock.unlock();
            storeroom.setPerson(lastPerson);

            Storeroom save = storeroomRepository.save(storeroom);
            storeroomList.add(save);

        }

        return storeroomList;
    }
}
