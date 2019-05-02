package com.jiang.demo.service.impl;


import com.jiang.demo.dto.shipment.ShipmentDTO;
import com.jiang.demo.dto.shipment.ShipmentForm;
import com.jiang.demo.entity.Shipment;
import com.jiang.demo.repository.ShipmentRepository;
import com.jiang.demo.service.ShipmentService;
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private ShipmentRepository shipmentRepository;
    @Autowired
    public void setShipmentRepository(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public PageDTO<ShipmentDTO> select(ShipmentForm shipmentForm) {
        Integer pageNum=shipmentForm.getPageNum();
        Integer pageSize=shipmentForm.getPageSize();
        //新建商品类  将form转换成goods
        Shipment shipment=new Shipment();
        BeanUtils.copyProperties(shipmentForm, shipment);


        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Page<Shipment> shipments = shipmentRepository.findAll(new ShipmentServiceImpl.MySpec(shipment),pageable);

        //封装分页
        PageDTO<ShipmentDTO> shipmentDTO =new PageDTO<>();
        BeanUtils.copyProperties(shipments, shipmentDTO);

        List<Shipment> content = shipments.getContent();
        List<ShipmentDTO> shipmentDTOList=new ArrayList<>();

        for (Shipment p:content) {
            shipmentDTOList.add(ShipmentDTO.convert(p));
        }
        shipmentDTO.setContent(shipmentDTOList);

        return shipmentDTO;
    }

    private class MySpec implements Specification<Shipment> {
        private Shipment shipment;
        private MySpec(Shipment shipment){
            this.shipment=shipment;
        }
        @Override
        public Predicate toPredicate(Root<Shipment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            String shipmentCode = shipment.getShipmentCode();
            String person = shipment.getPerson();
            Date ShipmentTime = shipment.getShipmentTime();
            //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
            List<Predicate> predicates = new ArrayList<>();

            //对客户端查询条件进行判断,并封装Predicate断言对象
            // isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
            if (StringUtils.isNotBlank(shipmentCode)) {
                Predicate predicate = cb.like(root.get("shipmentCode").as(String.class), "%"+shipmentCode+"%");
                predicates.add(predicate);
            }
            if (StringUtils.isNotBlank(person)) {
                Predicate predicate = cb.like(root.get("person").as(String.class), "%"+person+"%");
                predicates.add(predicate);
            }
            if (ShipmentTime!=null) {
                //大于或等于
                //lessThanOrEqualTo  小于或等于
                Predicate predicate = cb.greaterThanOrEqualTo(root.get("ShipmentTime").as(Date.class),ShipmentTime);
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
}
