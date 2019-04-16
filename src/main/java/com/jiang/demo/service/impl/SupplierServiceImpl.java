package com.jiang.demo.service.impl;

import com.jiang.demo.dto.Supplier.SupplierDTO;
import com.jiang.demo.dto.Supplier.SupplierForm;
import com.jiang.demo.entity.Supplier;
import com.jiang.demo.repository.SupplierRepository;
import com.jiang.demo.service.SupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/6
 */

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierDTO selectSupplier(Integer id){
        Supplier supplier = supplierRepository.findById(id).get();

        SupplierDTO supplierDTO=SupplierDTO.convert(supplier);

        return supplierDTO;
    }

    public SupplierDTO insertSupplier(SupplierForm supplierForm){
        Supplier supplier=new Supplier();
        BeanUtils.copyProperties(supplierForm, supplier);
        Supplier save = supplierRepository.save(supplier);

        SupplierDTO supplierDTO=SupplierDTO.convert(save);
        return supplierDTO;
    }

    public List<SupplierDTO> selectAll(){
        List<Supplier> all = supplierRepository.findAll();
        List<SupplierDTO> supplierDTOList=new ArrayList<>();
        for(Supplier i:all){
            supplierDTOList.add(SupplierDTO.convert(i));
        }
        return supplierDTOList;
    }
}
