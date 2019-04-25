package com.jiang.demo.service.impl;

import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailForm;
import com.jiang.demo.entity.Purchase;
import com.jiang.demo.entity.PurchaseDetail;
import com.jiang.demo.repository.GoodsRepository;
import com.jiang.demo.repository.PurchaseDetailRepository;
import com.jiang.demo.repository.PurchaseRepository;
import com.jiang.demo.service.PurchaseDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/23
 */

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

    private PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    public void setPurchaseDetailRepository(PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
    }
    private GoodsRepository goodsRepository;
    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    private PurchaseRepository purchaseRepository;
    @Autowired
    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional
    public List<PurchaseDetailDTO> insertPurchaseDetail(PurchaseForm purchaseForm){
        //商品数量和商品id


        /*将前者赋值给后者*/
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(purchaseForm, purchase);
        //保存 并且  得到订单
        Purchase save1 = purchaseRepository.save(purchase);

        List<PurchaseDetailDTO> purchaseDetailDTOList=new ArrayList<>();

        for(PurchaseDetailForm p: purchaseForm.getPurchaseDetailForms()) {

            PurchaseDetail purchaseDetail=new PurchaseDetail();

            Integer goodsNumber = p.getGoodsNumber();
            System.out.println("goodsNumber"+goodsNumber);
            purchaseDetail.setGoodsNumber(goodsNumber);

            Integer goodsId = p.getGoodsId();
            System.out.println("goodsId"+goodsId);
            //根据商品id查询商品
            purchaseDetail.setGoods(goodsRepository.findById(goodsId).orElse(null));


            System.out.println("商品查询完了！");
            purchaseDetail.setPurchase(save1);
            //保存订单详情
            System.out.println("保存订单详情");
            PurchaseDetail save = purchaseDetailRepository.save(purchaseDetail);

            purchaseDetailDTOList.add(PurchaseDetailDTO.convert(save));
        }

           return purchaseDetailDTOList;

    }
}
