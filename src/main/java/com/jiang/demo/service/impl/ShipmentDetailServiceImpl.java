package com.jiang.demo.service.impl;

import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailForm;
import com.jiang.demo.dto.shipment.ShipmentDTO;
import com.jiang.demo.dto.shipment.ShipmentForm;
import com.jiang.demo.dto.shipmentDetail.ShipmentDetailDTO;
import com.jiang.demo.dto.shipmentDetail.ShipmentDetailForm;
import com.jiang.demo.entity.Purchase;
import com.jiang.demo.entity.PurchaseDetail;
import com.jiang.demo.entity.Shipment;
import com.jiang.demo.entity.ShipmentDetail;
import com.jiang.demo.repository.GoodsRepository;
import com.jiang.demo.repository.ShipmentDetailRepository;
import com.jiang.demo.repository.ShipmentRepository;
import com.jiang.demo.service.ShipmentDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    @Transactional
    public List<ShipmentDetailDTO> insertShipmentDetail(ShipmentForm shipmentForm) {
        /*将前者赋值给后者*/
        Shipment shipment = new Shipment();
        BeanUtils.copyProperties(shipmentForm, shipment);
        //保存 并且  得到订单
        Shipment save1 = shipmentRepository.save(shipment);


        List<ShipmentDetailDTO> shipmentDetailDTOList=new ArrayList<>();
        for(ShipmentDetailForm s: shipmentForm.getShipmentDetailForms()) {

            ShipmentDetail shipmentDetail=new ShipmentDetail();

            Integer goodsNumber = s.getGoodsNumber();
            System.out.println("goodsNumber"+goodsNumber);
            shipmentDetail.setGoodsNumber(goodsNumber);

            Integer goodsId = s.getGoodsId();

            //根据商品id查询商品
            shipmentDetail.setGoods(goodsRepository.findById(goodsId).orElse(null));

            System.out.println("商品查询完了！");
            shipmentDetail.setShipment(save1);
            //保存订单详情
            System.out.println("保存订单详情");
            ShipmentDetail save = shipmentDetailRepository.save(shipmentDetail);

            shipmentDetailDTOList.add(ShipmentDetailDTO.convert(save));
        }

        return shipmentDetailDTOList;
    }
}
