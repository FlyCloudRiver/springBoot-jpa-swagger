package com.jiang.demo.service.impl;

import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.dto.goods.GoodsForm;
import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.Goods;
import com.jiang.demo.entity.Supplier;
import com.jiang.demo.repository.CategoryRepository;
import com.jiang.demo.repository.GoodsRepository;
import com.jiang.demo.repository.SupplierRepository;
import com.jiang.demo.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

/**
 * Author: 江云飞
 * Date:   2019/4/6
 */

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public Goods insertGoods(GoodsForm goodsForm){

        Goods goods=new Goods();
        /*将前者赋值给后者*/
        BeanUtils.copyProperties(goodsForm, goods);

        Category category = categoryRepository.findById(goodsForm.getCategoryId()).get();
        goods.setCategory(category);

        Supplier supplier=supplierRepository.findById(goodsForm.getSupplierId()).get();
        goods.setSupplier(supplier);


        Goods save = goodsRepository.save(goods);

        return  save;
    }

    //动态查询:
    public List findByDynamicCases(GoodsForm goodsForm){

        Goods goods=new Goods();
        BeanUtils.copyProperties(goods, goodsForm);

        return goodsRepository.findAll(new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path<Object> goodsName = root.get("goodsName");

                Predicate name = criteriaBuilder.like(goodsName.as(String.class),"%"+goods.getGoodsName()+"%");

                criteriaQuery.where(name);
                return null;
            }
        });
    }

    public Goods findGoodsDTOById(Integer id){
        Goods goods = goodsRepository.findById(id).get();

        return  goods;
    }
}
