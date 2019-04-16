package com.jiang.demo.service;

import com.jiang.demo.config.PageDTO;
import com.jiang.demo.dto.Supplier.SupplierDTO;
import com.jiang.demo.dto.Supplier.SupplierForm;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/6
 */
public interface SupplierService {
    SupplierDTO selectSupplier(Integer id);

    SupplierDTO insertSupplier(SupplierForm supplierForm);

    void deleteSupplierById(Integer id);

    PageDTO<SupplierDTO> findByDynamicCases(SupplierForm supplierForm, Integer pageNum, Integer pageSize);

    SupplierDTO updateSupplier(SupplierForm supplierForm,Integer id);
}
