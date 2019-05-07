package com.jiang.demo.service.impl;

import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailForm;
import com.jiang.demo.entity.Purchase;
import com.jiang.demo.entity.PurchaseDetail;
import com.jiang.demo.entity.Storeroom;
import com.jiang.demo.repository.*;
import com.jiang.demo.service.PurchaseDetailService;
import com.jiang.demo.service.StoreroomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    private StoreroomService storeroomService;
    @Autowired
    public void setStoreroomService(StoreroomService storeroomService) {
        this.storeroomService = storeroomService;
    }

    private StoreroomRepository storeroomRepository;
    @Autowired
    public void setStoreroomRepository(StoreroomRepository storeroomRepository) {
        this.storeroomRepository = storeroomRepository;
    }

    /*添加订单  */
    @Transactional
    public List<PurchaseDetailDTO> insertPurchaseDetail(PurchaseForm purchaseForm){

        /*将前者赋值给后者*/
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(purchaseForm, purchase);
        //保存 并且  得到订单
        Purchase save1 = purchaseRepository.save(purchase);

        List<PurchaseDetailDTO> purchaseDetailDTOList=new ArrayList<>();

        //商品id  数量集合
       // Map<Integer,Integer> map = new HashMap<>();
        //Map<Integer,Integer> map2 = new HashMap<>();
        //时间

        Date time= purchaseForm.getPurchaseTime();
        System.out.println("time"+time);
        //操作人
       // String lastPerson=purchaseForm.getPerson();
        for(PurchaseDetailForm p: purchaseForm.getPurchaseDetailForms()) {

            PurchaseDetail purchaseDetail=new PurchaseDetail();

            Integer goodsNumber = p.getGoodsNumber();
            System.out.println("goodsNumber"+goodsNumber);
            purchaseDetail.setGoodsNumber(goodsNumber);

            Integer goodsId = p.getGoodsId();
            System.out.println("goodsId"+goodsId);

            //此处查询库房中是否有该商品  如果有放入map  如果没有放入map2
            /*Storeroom byGoodsId = storeroomRepository.findByGoodsId(goodsId);
            if(byGoodsId!=null){
                map.put(goodsId,goodsNumber);
            }else{
                map2.put(goodsId,goodsNumber);
            }*/

            //根据商品id查询商品
            purchaseDetail.setGoods(goodsRepository.findById(goodsId).orElse(null));


            System.out.println("商品查询完了！");
            purchaseDetail.setPurchase(save1);
            //保存订单详情
            System.out.println("保存订单详情");
            PurchaseDetail save = purchaseDetailRepository.save(purchaseDetail);

            purchaseDetailDTOList.add(PurchaseDetailDTO.convert(save));
        }

        /*增加订单的时候判断库房是否有该商品集合  如果有就更新库房  没有就创建*/
        //定义两个map 一个装有的  一个装没的  没的创建  有的更新
          /* storeroomService.updateStoreroom(map,time,lastPerson);

           if(map2.size()!=0){
               storeroomService.insertStoreroom(map2,time,lastPerson);
           }*/
           return purchaseDetailDTOList;

    }
}
