package com.jiang.demo.service.impl;

import com.jiang.demo.entity.Supplier;
import com.jiang.demo.repository.SupplierRepository;
import com.jiang.demo.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: 江云飞
 * Date:   2019/4/6
 */

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier selectSupplier(Integer id){
        Supplier supplier = supplierRepository.findById(id).get();
        return supplier;
    }
}
