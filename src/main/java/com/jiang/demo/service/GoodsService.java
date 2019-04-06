package com.jiang.demo.service;

import com.jiang.demo.dto.goods.GoodsForm;
import com.jiang.demo.entity.Goods;


import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/6
 */
public interface GoodsService {

    Goods insertGoods(GoodsForm goodsForm);
    List findByDynamicCases(GoodsForm goodsForm);
    Goods findGoodsDTOById(Integer id);
}
