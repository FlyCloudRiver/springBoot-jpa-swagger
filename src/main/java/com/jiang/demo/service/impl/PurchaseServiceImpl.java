package com.jiang.demo.service.impl;

import com.jiang.demo.dto.purchase.PurchaseDTO;
import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.entity.Purchase;
import com.jiang.demo.repository.PurchaseDetailRepository;
import com.jiang.demo.repository.PurchaseRepository;
import com.jiang.demo.service.PurchaseService;
import com.jiang.demo.utils.PageDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;
    @Autowired
    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }
    private PurchaseDetailRepository purchaseDetailRepository;
    @Autowired
    public void setPurchaseDetailRepository(PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    @Override
    public List<PurchaseDTO> findAll(){
        List<Purchase> all = purchaseRepository.findAll();
        List<PurchaseDTO> list=new ArrayList<>();
        for (Purchase p:all) {
            list.add(PurchaseDTO.convert(p));
        }
        return  list;
    }

    //动态分页查询（编号，时间，姓名）
    public PageDTO<PurchaseDTO> select(PurchaseForm purchaseForm, Integer pageNum, Integer pageSize){

        //新建商品类  将form转换成goods
        Purchase purchase=new Purchase();
        BeanUtils.copyProperties(purchaseForm, purchase);


        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Page<Purchase> purchases = purchaseRepository.findAll(new MySpec(purchase),pageable);

        //封装分页
        PageDTO<PurchaseDTO> purchaseDTO =new PageDTO<>();
        BeanUtils.copyProperties(purchases, purchaseDTO);

        List<Purchase> content = purchases.getContent();
        List<PurchaseDTO> purchaseDTOList=new ArrayList<>();

        for (Purchase p:content) {
            purchaseDTOList.add(PurchaseDTO.convert(p));
        }
        purchaseDTO.setContent(purchaseDTOList);

        return purchaseDTO;
    }

    private class MySpec implements Specification<Purchase> {
        private Purchase purchase;
        private MySpec(Purchase purchase){
            this.purchase=purchase;
        }
        @Override
        public Predicate toPredicate(Root<Purchase> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            String purchaseCode = purchase.getPurchaseCode();
            String person = purchase.getPerson();
            Date purchaseTime = purchase.getPurchaseTime();
            //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
            List<Predicate> predicates = new ArrayList<>();

            //对客户端查询条件进行判断,并封装Predicate断言对象
            // isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
            if (StringUtils.isNotBlank(purchaseCode)) {
                Predicate predicate = cb.like(root.get("purchaseCode").as(String.class), "%"+purchaseCode+"%");
                predicates.add(predicate);
            }
            if (StringUtils.isNotBlank(person)) {
                Predicate predicate = cb.like(root.get("person").as(String.class), "%"+person+"%");
                predicates.add(predicate);
            }
            if (purchaseTime!=null) {
                //大于或等于
                //lessThanOrEqualTo  小于或等于
                Predicate predicate = cb.greaterThanOrEqualTo(root.get("purchaseTime").as(Date.class),purchaseTime);
                predicates.add(predicate);
            }

            //判断结合中是否有数据
            if (predicates.size() == 0) {
                return null;
            }

            //将集合转化为CriteriaBuilder所需要的Predicate[]
            Predicate[] predicateArr = new Predicate[predicates.size()];
            predicateArr = predicates.toArray(predicateArr);

            // 返回所有获取的条件： 条件 or 条件 or 条件 or 条件
            return cb.and(predicateArr);
            //return cb.or(predicateArr);
        }

    }
    //添加购买记录
    @Override

    public Purchase insertPurchase(PurchaseForm purchaseForm) {

        //首先添加一个订单
        Purchase purchase2 = new Purchase();
        //BeanUtils.copyProperties(purchaseForm, purchase2);
        //Purchase save3 = purchaseRepository.save(purchase2);
        return purchase2;
    }
      /*  //商品详情集合  添加商品详情
        List<PurchaseDetail> purchaseDetails = new ArrayList<>();

        List<PurchaseDetailForm> purchaseDetailForms = purchaseForm.getPurchaseDetailForms();

        for (PurchaseDetailForm p:purchaseDetailForms) {
            PurchaseDetail purchaseDetail=new PurchaseDetail();
            //根据商品id获取商品
            Goods goods = goodsRepository.findById(p.getGoodsId()).orElse(null);
            //将商品保存到商品详情中
            *//*将前者赋值给后者*//*
            purchaseDetail.setGoodsNumber(p.getGoodsNumber());
            purchaseDetail.setGoods(goods);

            purchaseDetail.setPurchase(save3);

            //保存商品详情
            PurchaseDetail save = purchaseDetailRepository.save(purchaseDetail);
            purchaseDetails.add(save);
        }

        //save3.setPurchaseDetails(purchaseDetails);

       // Purchase save = purchaseRepository.save(save3);

        List<PurchaseDetail> purchaseDetails1 = save3.getPurchaseDetails();//save3
        List<PurchaseDetailDTO> purchaseDetailDTOList = new ArrayList<>();
        for (PurchaseDetail pd:purchaseDetails1) {
            Goods goods = pd.getGoods();
            GoodsDTO convert = GoodsDTO.convert(goods);

            PurchaseDetailDTO convert1 = PurchaseDetailDTO.convert(pd);
            convert1.setGoodsDTO(convert);

            purchaseDetailDTOList.add(convert1);
        }
        PurchaseDTO convert = PurchaseDTO.convert(save3);//save3
        convert.setPurchaseDetailDTOS(purchaseDetailDTOList);

        System.out.println(convert);
        return convert;
*/




}