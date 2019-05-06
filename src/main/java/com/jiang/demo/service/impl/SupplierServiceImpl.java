package com.jiang.demo.service.impl;

import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.dto.supplier.SupplierDTO;
import com.jiang.demo.dto.supplier.SupplierForm;
import com.jiang.demo.entity.Supplier;
import com.jiang.demo.repository.SupplierRepository;
import com.jiang.demo.service.SupplierService;
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
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/6
 */

@Service
public class SupplierServiceImpl implements SupplierService {

    // 通过set方法注入
    private SupplierRepository supplierRepository;
    @Autowired
    public void setSupplierRepository(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierDTO insertSupplier(SupplierForm supplierForm){
        Supplier supplier=new Supplier();
        BeanUtils.copyProperties(supplierForm, supplier);

        Supplier save = supplierRepository.save(supplier);

        return SupplierDTO.convert(save);
    }

    public void deleteSupplierById(Integer id){
        supplierRepository.deleteById(id);
    }

    public SupplierDTO selectSupplier(Integer id){
        //如果没找到返回空
        Supplier supplier = supplierRepository.findById(id).orElse(null);

        return SupplierDTO.convert(supplier);
    }

    public SupplierDTO updateSupplier(SupplierForm supplierForm,Integer id){

        Supplier supplier = supplierRepository.findById(id).orElse(null);
        Supplier supplier1=new Supplier();
        if(supplier==null){
            supplier=supplier1;
        }
        BeanUtils.copyProperties(supplierForm, supplier);

        System.out.println("supplier="+supplier);
        Supplier save = supplierRepository.save(supplier);

        return SupplierDTO.convert(save);
    }

    //动态查询:
    public PageDTO<SupplierDTO> findByDynamicCases(SupplierForm supplierForm){

        Integer pageNum = supplierForm.getPageNum();
        Integer pageSize = supplierForm.getPageSize();
        Supplier supplier=new Supplier();
        BeanUtils.copyProperties(supplierForm, supplier);

        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Page<Supplier> suppliers = supplierRepository.findAll(new MySpec(supplierForm),pageable);
        //封装分页
        PageDTO<SupplierDTO> pageDTO =new PageDTO<>();
        BeanUtils.copyProperties(suppliers, pageDTO);

        List<Supplier> content = suppliers.getContent();
        List<SupplierDTO> supplierDTOList=new ArrayList<>();
        for (Supplier s:content) {
            supplierDTOList.add(SupplierDTO.convert(s));
        }
        pageDTO.setContent(supplierDTOList);

        return pageDTO;

    }
    private class MySpec implements Specification<Supplier> {
        private SupplierForm supplierForm;
        private MySpec(SupplierForm supplierForm){
            this.supplierForm=supplierForm;
        }
        @Override
        public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            String supplierName = supplierForm.getSupplierName();
            String supplierAddress = supplierForm.getSupplierAddress();
            String supplierPhone = supplierForm.getSupplierPhone();
            String supplierEvaluate = supplierForm.getSupplierEvaluate();
            String supplierAddressDetail = supplierForm.getSupplierAddressDetail();
            //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
            List<Predicate> predicates = new ArrayList<>();

            //对客户端查询条件进行判断,并封装Predicate断言对象
            // isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
            if (StringUtils.isNotBlank(supplierAddressDetail)) {
                Predicate predicate = cb.like(root.get("supplierAddressDetail").as(String.class), "%"+supplierAddressDetail+"%");
                predicates.add(predicate);
            }
            if (StringUtils.isNotBlank(supplierEvaluate)) {
                Predicate predicate = cb.like(root.get("supplierEvaluate").as(String.class), "%"+supplierEvaluate+"%");
                predicates.add(predicate);
            }
            if (StringUtils.isNotBlank(supplierName)) {
                Predicate predicate = cb.like(root.get("supplierName").as(String.class), "%"+supplierName+"%");
                predicates.add(predicate);
            }
            if (StringUtils.isNotBlank(supplierAddress)) {
                Predicate predicate = cb.like(root.get("supplierAddress").as(String.class), "%"+supplierAddress+"%");
                predicates.add(predicate);
            }
            if (StringUtils.isNotBlank(supplierPhone)) {
                Predicate predicate = cb.like(root.get("supplierPhone").as(String.class), "%"+supplierPhone+"%");
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
