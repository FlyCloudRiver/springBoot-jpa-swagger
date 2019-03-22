package com.jiang.demo.service;


import com.jiang.demo.dto.CommodityDTO;
import com.jiang.demo.dto.CommodityForm;
import com.jiang.demo.entity.Commodity;

import com.jiang.demo.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommodityService {
    @Autowired
    private CommodityRepository commodityRepository;


    /*添加商品*/
    public Integer insertCommodity(CommodityForm commodityForm){
        Integer commodity1=commodityRepository.saveCommodity(commodityForm.getCommodityId(),commodityForm.getCommodityName());
        return commodity1;
    }

    /**/
}
