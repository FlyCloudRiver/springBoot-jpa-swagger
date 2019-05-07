package com.jiang.demo.service;

import com.jiang.demo.dto.purchase.PurchaseDTO;
import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/23
 */
public interface PurchaseDetailService {

    List<PurchaseDetailDTO> insertPurchaseDetail(PurchaseForm purchaseForm);

    void update(PurchaseDTO purchaseDTO);
}
