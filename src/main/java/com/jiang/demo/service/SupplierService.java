package com.jiang.demo.service;

import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.dto.supplier.SupplierDTO;
import com.jiang.demo.dto.supplier.SupplierForm;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/6
 */
public interface SupplierService {
    SupplierDTO selectSupplier(Integer id);

    SupplierDTO insertSupplier(SupplierForm supplierForm);

    void deleteSupplierById(Integer id);

    PageDTO<SupplierDTO> findByDynamicCases(SupplierForm supplierForm);

    SupplierDTO updateSupplier(SupplierForm supplierForm,Integer id);

    List<SupplierDTO> selectAll();
}
