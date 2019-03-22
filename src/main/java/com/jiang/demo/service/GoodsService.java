package com.jiang.demo.service;


import com.jiang.demo.entity.Goods;

import com.jiang.demo.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    /*添加商品*/
    public Goods insertGoods(Goods goods){
        Goods goods1=goodsRepository.save(goods);
        return goods1;
    }

    /*查询商品*/
    public List<Goods> selectGoods(){
        List<Goods> goodsList=goodsRepository.findAll();
        return goodsList;
    }
    /*更新商品*/
    public Goods updateGoods(Goods goods){
        Goods goods1 = goodsRepository.save(goods);
        return goods1;
    }

}
