package com.jiang.demo.service.impl;

import com.jiang.demo.dto.Storeroom.StoreroomDTO;
import com.jiang.demo.dto.Storeroom.StoreroomForm;
import com.jiang.demo.dto.purchase.PurchaseStorageFrom;
import com.jiang.demo.dto.shipment.ShipmentStorageFrom;
import com.jiang.demo.entity.*;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.*;
import com.jiang.demo.service.StoreroomService;
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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: 江云飞
 * Date:   2019/4/20
 */

@Service
public class StoreroomServiceImpl implements StoreroomService {

    private StoreroomRepository storeroomRepository;

    @Autowired
    public void setStoreroomRepository(StoreroomRepository storeroomRepository) {
        this.storeroomRepository = storeroomRepository;
    }

    private PurchaseRepository purchaseRepository;
    @Autowired
    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    private ShipmentRepository shipmentRepository;
    @Autowired
    public void setShipmentRepository(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }


    private GoodsRepository goodsRepository;
    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    @Transactional
    //入库
    public void insertStorage(PurchaseStorageFrom purchaseStorageFrom) {

        Date date = new Date();
        //1.(订单)查询订单的storage值  如果为true  返回错误   如果为false  将值改为true 进行下一步
        Purchase purchase = purchaseRepository.findById(purchaseStorageFrom.getId()).orElse(null);
        if(purchase==null){
            throw new MyException(-1,"该订单不存在");
        }
        if(purchase.getStorage()){
            throw new MyException(2,"该订单已入库");
        }
        purchase.setStorage(true);

        //商品入库时间
        purchase.setStoreTime(date);
        Purchase save = purchaseRepository.save(purchase);
        //2.添加库房(得到采购单详情——商品id 商品数量)

        List<PurchaseDetail> purchaseDetails = save.getPurchaseDetails();
        for (PurchaseDetail p:purchaseDetails) {
            Integer goodsId = p.getGoods().getId();
            Integer goodsNumber = p.getGoodsNumber();
            //此处查询库房中是否有该商品  如果有 更新商品数量   如果没有 添加商品到库存
            //Storeroom storeroom = storeroomRepository.findByGoodsId(goodsId);

            Storeroom storeroom = new Storeroom();

            //保存商品
            storeroom.setGoods(goodsRepository.findById(goodsId).orElse(null));
            storeroom.setAmount(0);
            Storeroom storeroom2 = storeroomRepository.save(storeroom);

            //库存量
            Integer amount = storeroomRepository.findByGoodsId(goodsId).get(1).getAmount();
            if(amount!=null){
                storeroom2.setAmount(amount +goodsNumber );
            }


            //操作人
            storeroom2.setPerson(purchaseStorageFrom.getPerson());
            //出入库类型
            storeroom2.setStyle("商品入库");
            //出入库时间
            storeroom2.setUpdateTime(date);
            //出入库数量
            storeroom2.setNumber(goodsNumber);
            //保存库存
            storeroomRepository.save(storeroom2);
           /*
                Storeroom storeroom2 = new Storeroom();

                storeroom2.setGoods(goodsRepository.findById(goodsId).orElse(null));
                storeroom2.setUpdateTime(date);
                //库存量
                storeroom2.setAmount(goodsNumber );
                storeroom2.setPerson(purchaseStorageFrom.getPerson());
                //更新库存
                storeroomRepository.save(storeroom2);*/


        }

    }

    @Override
    @Transactional
    public void outputStorage(ShipmentStorageFrom shipmentStorageFrom) {

        Date date = new Date();
        //1.(订单)查询订单的storage值  如果为true  返回错误   如果为false  将值改为true 进行下一步
        Shipment shipment = shipmentRepository.findById(shipmentStorageFrom.getId()).orElse(null);
        if (shipment == null) {
            throw new MyException(-1, "该订单不存在");
        }
        if (shipment.getStorage()) {
            throw new MyException(2, "该订单已出库");
        }
        shipment.setStorage(true);
        shipment.setStoreTime(date);
        Shipment save = shipmentRepository.save(shipment);
        //2.添加库房(得到采购单详情——商品id 商品数量)

        List<ShipmentDetail> shipmentDetailList = save.getShipmentDetailList();
        for (ShipmentDetail p : shipmentDetailList) {
            Integer goodsId = p.getGoods().getId();
            Integer goodsNumber = p.getGoodsNumber();
            //此处查询库房中是否有该商品  如果有 更新商品数量   如果没有或者 出库数量大于库存量返回错误
            Storeroom storeroom = new Storeroom();
            //保存商品
            storeroom.setGoods(goodsRepository.findById(goodsId).orElse(null));
            storeroom.setAmount(0);
            Storeroom storeroom2 = storeroomRepository.save(storeroom);

            //库存量
            Integer amount = storeroomRepository.findByGoodsId(goodsId).get(1).getAmount();
            if(amount!=null){
                storeroom2.setAmount(amount-goodsNumber );
            }

            //操作人
            storeroom.setPerson(shipmentStorageFrom.getPerson());
            //出入库类型
            storeroom.setStyle("商品出库");
            //出入库时间
            storeroom.setUpdateTime(date);
            //出入库数量
            storeroom.setNumber(goodsNumber);
            //保存库存
            storeroomRepository.save(storeroom);
        }


    }

    //根据商品id查询交易记录
    public List<Storeroom> selectInfo(Integer goodsId){
        List<Storeroom> storeroomList = storeroomRepository.selectInfo(goodsId);
        return storeroomList;
    }

    @Transactional
    public PageDTO<StoreroomDTO> selectAll(Integer pageNum,Integer pageSize) {

        List<Storeroom> storeroomList = storeroomRepository.selectAll(pageNum - 1, pageSize);

        PageDTO<StoreroomDTO> pageDTO = new PageDTO<>();
        Integer size = storeroomRepository.selectSize();


        pageDTO.setTotalElements(Long.valueOf(size));

        List<StoreroomDTO> list = new ArrayList<>();
        for (Storeroom storeroom : storeroomList) {
            list.add(StoreroomDTO.convert(storeroom));
        }

        pageDTO.setContent(list);

        return pageDTO;
    }
    @Override
    public PageDTO<StoreroomDTO> select(StoreroomForm storeroomForm) {

        Integer pageNum=storeroomForm.getPageNum();
        Integer pageSize=storeroomForm.getPageSize();
        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        //动态分页查出的原始值
        MySpec mySpec = new MySpec(storeroomForm);
        Page<Storeroom> storerooms = storeroomRepository.findAll(mySpec, pageable);

        //封装分页
        PageDTO<StoreroomDTO> pageDTO = new PageDTO<>();
        //将原始封装转换为自定义的分页封装类
        BeanUtils.copyProperties(storerooms, pageDTO);

        List<Storeroom> content = storerooms.getContent();
        List<StoreroomDTO> storeroomDTOS = new ArrayList<>();

        for (Storeroom s : content) {
            storeroomDTOS.add(StoreroomDTO.convert(s));
        }
        pageDTO.setContent(storeroomDTOS);

        return pageDTO;
    }

    private class MySpec implements Specification<Storeroom> {
        private StoreroomForm storeroomForm;

        private MySpec(StoreroomForm storeroomForm) {
            this.storeroomForm = storeroomForm;
        }

        @Override
        public Predicate toPredicate(Root<Storeroom> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            Integer amount = storeroomForm.getAmount();
            String person = storeroomForm.getPerson();
/*
            String goodsCode = storeroomForm.getGoodsCode();
            String goodsName = storeroomForm.getGoodsName();*/
            //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
            List<Predicate> predicates = new ArrayList<>();

            //对客户端查询条件进行判断,并封装Predicate断言对象
            // isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
            if (StringUtils.isNotBlank(person)) {
                Predicate predicate = cb.like(root.get("person").as(String.class), "%" + person + "%");
                predicates.add(predicate);
            }
/*
            if (startTime!=null&&endTime==null) {

                Predicate predicate = cb.greaterThanOrEqualTo(root.get("updateTime").as(Date.class),startTime);
                predicates.add(predicate);
            }
            if (endTime!=null&&startTime==null) {
                Predicate predicate = cb.lessThanOrEqualTo(root.get("updateTime").as(Date.class),endTime);
                predicates.add(predicate);
            }
            if (endTime!=null&&startTime!=null) {
                Predicate predicate1 = cb.greaterThanOrEqualTo(root.get("updateTime").as(Date.class),startTime);
                Predicate predicate2 = cb.lessThanOrEqualTo(root.get("updateTime").as(Date.class),endTime);
                predicates.add(predicate1);
                predicates.add(predicate2);
            }*/


            if (amount != null) {
                //不超过
                Predicate predicate = cb.lessThanOrEqualTo(root.get("amount").as(Integer.class), amount);
                predicates.add(predicate);
            }
            //商品编号 模糊查询
            if (StringUtils.isNotBlank(storeroomForm.getGoodsCode())){
                //Predicate predicate = cb.like(root.get(storeroom.getGoods().getGoodsCode()),"%"+storeroomForm.getGoodsCode()+"%");
                //ListJoin<Storeroom,Goods> join=root.join(root.getModel().getList("goods",Goods.class));
                Join<Storeroom,Goods> join=root.join("goods");
                Predicate predicate = cb.like(join.get("goodsCode").as(String.class),"%"+storeroomForm.getGoodsCode()+"%");
                predicates.add(predicate);
            }
            if(StringUtils.isNotBlank(storeroomForm.getGoodsName())){
                Join<Storeroom,Goods> join=root.join("goods");
                Predicate predicate = cb.like(join.get("goodsName").as(String.class),"%"+storeroomForm.getGoodsName()+"%");
                predicates.add(predicate);
            }

           // group by goods_id ORDER BY goods_id DESC;
            //判断结合中是否有数据
            if (predicates.size() == 0) {
                return null;
            }
            //将集合转化为CriteriaBuilder所需要的Predicate[]
            Predicate[] predicateArr = new Predicate[predicates.size()];
            predicateArr = predicates.toArray(predicateArr);

            // 返回所有获取的条件： 条件 or 条件 or 条件 or 条件

            //把Predicate应用到CriteriaQuery中去,因为还可以给CriteriaQuery添加其他的功能，比如排序、分组啥的
            query.where(predicateArr);


            Join<Storeroom,Goods> join=root.join("goods");
            //不起作用
            query.groupBy(join.get("id").as(Integer.class));

            query.orderBy(cb.desc(join.get("id").as(Integer.class)));

            //return cb.and(predicateArr);
            return query.getGroupRestriction();


        }

    }
}
