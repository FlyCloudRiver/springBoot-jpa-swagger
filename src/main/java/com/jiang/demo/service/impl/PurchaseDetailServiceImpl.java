package com.jiang.demo.service.impl;

import com.jiang.demo.dto.purchase.PurchaseDTO;
import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailForm;
import com.jiang.demo.entity.Purchase;
import com.jiang.demo.entity.PurchaseDetail;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.*;
import com.jiang.demo.service.PurchaseDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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


    public  static int pcount=1;
    /*生成采购单  */
    @Transactional
    public List<PurchaseDetailDTO> insertPurchaseDetail(PurchaseForm purchaseForm){

        /*将前者赋值给后者*/
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(purchaseForm, purchase);

        //保存 并且  得到订单
        Date date = new Date();
        purchase.setCreateTime(date);
        purchase.setUpdateTime(date);

        //订单编码
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(date);

        String purchaseCode=pcount+dateString;
        if(pcount<=10000){
            pcount++;
        }else{
            pcount=0;
        }
        purchase.setPurchaseCode(purchaseCode);

        Purchase save1 = purchaseRepository.save(purchase);

        List<PurchaseDetailDTO> purchaseDetailDTOList=new ArrayList<>();


        //操作人
       // String lastPerson=purchaseForm.getPerson();
        for(PurchaseDetailForm p: purchaseForm.getPurchaseDetailForms()) {
            PurchaseDetail purchaseDetail=new PurchaseDetail();
            Integer goodsNumber = p.getGoodsNumber();
            if(goodsNumber<=0){
                throw new MyException(-1,"所有商品数量必须大于零！");
            }
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

    @Override
    @Transactional
    public void update(PurchaseDTO purchaseDTO){
        //查询此订单是否已经进库
        Purchase purchase = purchaseRepository.findById(purchaseDTO.getId()).orElse(null);
        System.out.println(purchase);

        if(purchase!=null) {
            if (purchase.getStorage()){
                throw new MyException(-1, "商品已入库，不能修改");
            }
            //添加修改时间
            purchase.setUpdateTime(new Date());
            purchaseRepository.save(purchase);

            List<PurchaseDetailDTO> purchaseDetailDTOS = purchaseDTO.getPurchaseDetailDTOS();
            for (PurchaseDetailDTO p:purchaseDetailDTOS) {
                Integer id = p.getId();
                PurchaseDetail purchaseDetail = purchaseDetailRepository.findById(id).orElse(null);
                if(purchaseDetail!=null) {
                    if(p.getGoodsNumber()<0){
                        throw new MyException(-1,"商品数量不能为负数");
                    }
                    purchaseDetail.setGoodsNumber(p.getGoodsNumber());
                    //保存商品详情
                    purchaseDetailRepository.save(purchaseDetail);
                }else {
                    throw new MyException(-1,"修改的订单详情不存在");
                }

            }
        }else{
            throw new MyException(-1,"该订单不存在");
        }

    }


}
