package com.jiang.demo.service.impl;

import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.dto.Supplier.SupplierDTO;
import com.jiang.demo.dto.Supplier.SupplierForm;
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

    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierDTO insertSupplier(SupplierForm supplierForm){
        Supplier supplier=new Supplier();
        BeanUtils.copyProperties(supplierForm, supplier);
        Supplier save = supplierRepository.save(supplier);

        SupplierDTO supplierDTO=SupplierDTO.convert(save);
        return supplierDTO;
    }

    public void deleteSupplierById(Integer id){
        supplierRepository.deleteById(id);
    }

    public SupplierDTO selectSupplier(Integer id){
        Supplier supplier = supplierRepository.findById(id).get();
        SupplierDTO supplierDTO=SupplierDTO.convert(supplier);
        return supplierDTO;
    }

    public SupplierDTO updateSupplier(SupplierForm supplierForm,Integer id){
        Supplier supplier = supplierRepository.findById(id).get();
        BeanUtils.copyProperties(supplierForm, supplier);

        System.out.println("supplier="+supplier);
        Supplier save = supplierRepository.save(supplier);
        SupplierDTO convert = SupplierDTO.convert(save);
        return convert;
    }

    //动态查询:
    public PageDTO<SupplierDTO> findByDynamicCases(SupplierForm supplierForm, Integer pageNum, Integer pageSize){

        Supplier supplier=new Supplier();
        BeanUtils.copyProperties(supplierForm, supplier);

        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Page<Supplier> suppliers = supplierRepository.findAll(new MySpec(supplier),pageable);
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
    public class MySpec implements Specification<Supplier> {
        private Supplier supplier;
        public MySpec(Supplier supplier){
            this.supplier=supplier;
        }
        @Override
        public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            String supplierName = supplier.getSupplierName();
            String supplierAddress = supplier.getSupplierAddress();
            String supplierPhone = supplier.getSupplierPhone();
            //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
            List<Predicate> predicates = new ArrayList<>();

            //对客户端查询条件进行判断,并封装Predicate断言对象
            // isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
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
