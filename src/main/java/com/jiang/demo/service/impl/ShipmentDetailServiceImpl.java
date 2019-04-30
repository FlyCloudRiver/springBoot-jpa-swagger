package com.jiang.demo.service.impl;

import com.jiang.demo.dto.shipment.ShipmentForm;
import com.jiang.demo.dto.shipmentDetail.ShipmentDetailDTO;
import com.jiang.demo.dto.shipmentDetail.ShipmentDetailForm;
import com.jiang.demo.entity.Shipment;
import com.jiang.demo.entity.ShipmentDetail;
import com.jiang.demo.repository.GoodsRepository;
import com.jiang.demo.repository.ShipmentDetailRepository;
import com.jiang.demo.repository.ShipmentRepository;
import com.jiang.demo.service.ShipmentDetailService;
import com.jiang.demo.service.StoreroomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Author: 江云飞
 * Date:   2019/4/25
 */

@Service
public class ShipmentDetailServiceImpl implements ShipmentDetailService {

    private ShipmentRepository shipmentRepository;
    @Autowired
    public void setShipmentRepository(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    private ShipmentDetailRepository shipmentDetailRepository;
    @Autowired
    public void setShipmentDetailRepository(ShipmentDetailRepository shipmentDetailRepository) {
        this.shipmentDetailRepository = shipmentDetailRepository;
    }

    private GoodsRepository goodsRepository;
    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    private StoreroomService storeroomService;
    @Autowired
    public void setStoreroomService(StoreroomService storeroomService) {
        this.storeroomService = storeroomService;
    }

    @Override
    @Transactional
    public List<ShipmentDetailDTO> insertShipmentDetail(ShipmentForm shipmentForm) {
        /*将前者赋值给后者*/
        Shipment shipment = new Shipment();
        BeanUtils.copyProperties(shipmentForm, shipment);
        //保存 并且  得到订单
        Shipment save1 = shipmentRepository.save(shipment);


        List<ShipmentDetailDTO> shipmentDetailDTOList=new ArrayList<>();

        //商品id  数量集合
        Map<Integer,Integer> map = new HashMap<>();
        //时间

        Date time= shipmentForm.getShipmentTime();
        System.out.println("time"+time);
        //操作人
        String lastPerson=shipmentForm.getPerson();

        for(ShipmentDetailForm s: shipmentForm.getShipmentDetailForms()) {

            ShipmentDetail shipmentDetail=new ShipmentDetail();

            Integer goodsNumber = s.getGoodsNumber();
            System.out.println("goodsNumber"+goodsNumber);
            shipmentDetail.setGoodsNumber(goodsNumber);

            Integer goodsId = s.getGoodsId();
            map.put(goodsId,-goodsNumber);
            //根据商品id查询商品
            shipmentDetail.setGoods(goodsRepository.findById(goodsId).orElse(null));

            System.out.println("商品查询完了！");
            shipmentDetail.setShipment(save1);
            //保存订单详情
            System.out.println("保存订单详情");
            ShipmentDetail save = shipmentDetailRepository.save(shipmentDetail);

            shipmentDetailDTOList.add(ShipmentDetailDTO.convert(save));
        }

        /*出售商品的时候更新库房*/
        storeroomService.updateStoreroom(map,time,lastPerson);

        return shipmentDetailDTOList;
    }
}
