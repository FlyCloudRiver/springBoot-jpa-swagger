package com.jiang.demo.service;

import com.jiang.demo.dto.goods.GoodsForm;
import com.jiang.demo.entity.Goods;
import org.springframework.data.domain.Page;


import java.util.Iterator;

/**
 * Author: 江云飞
 * Date:   2019/4/6
 */
public interface GoodsService {

    Goods insertGoods(GoodsForm goodsForm);
    Page<Goods> findByDynamicCases(GoodsForm goodsForm, Integer pageNum, Integer pageSize);
    Goods findGoodsDTOById(Integer id);
}
