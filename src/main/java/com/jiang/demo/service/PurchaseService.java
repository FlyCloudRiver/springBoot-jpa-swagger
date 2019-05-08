package com.jiang.demo.service;

import com.jiang.demo.dto.purchase.PurchaseDTO;
import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.utils.PageDTO;



/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public interface PurchaseService {


    //动态分页查询（编号，时间，姓名）
    PageDTO<PurchaseDTO> select(PurchaseForm purchaseForm);

    void delete(Integer id);

    PurchaseDTO selectById(Integer id);
}
