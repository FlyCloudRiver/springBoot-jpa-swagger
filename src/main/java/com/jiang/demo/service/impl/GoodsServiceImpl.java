package com.jiang.demo.service.impl;

import com.jiang.demo.config.PageDTO;
import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.dto.goods.GoodsForm;
import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.Goods;
import com.jiang.demo.entity.Supplier;
import com.jiang.demo.repository.CategoryRepository;
import com.jiang.demo.repository.GoodsRepository;
import com.jiang.demo.repository.SupplierRepository;
import com.jiang.demo.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.*;
import java.util.*;
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



    public Goods findGoodsDTOById(Integer id){
        Goods goods = goodsRepository.findById(id).get();

        return  goods;
    }


    //动态查询:
    public PageDTO<GoodsDTO> findByDynamicCases(GoodsForm goodsForm,Integer pageNum,Integer pageSize){

        //新建商品类  将form转换成goods
        Goods goods=new Goods();
        BeanUtils.copyProperties(goodsForm, goods);

        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum,pageSize,sort);
        Page<Goods> gooodsies = goodsRepository.findAll(new MySpec(goods),pageable);

        //封装分页
        PageDTO<GoodsDTO> pageDTO =new PageDTO<>();
        BeanUtils.copyProperties(gooodsies, pageDTO);
       /* pageDTO.setTotalElements(gooodsies.getTotalElements());
        pageDTO.setTotalPages(gooodsies.getTotalPages());
        pageDTO.setFirst(gooodsies.isFirst());
        pageDTO.setLast(gooodsies.isLast());
        pageDTO.setNumberOfElements(gooodsies.getNumberOfElements());
        pageDTO.setNumber(gooodsies.getNumber());
        pageDTO.setSize(gooodsies.getSize());
        pageDTO.setHasContent(gooodsies.hasContent());*/
        List<Goods> content = gooodsies.getContent();
        List<GoodsDTO> goodsDTOList=new ArrayList<>();
        for (Goods g:content) {
            goodsDTOList.add(GoodsDTO.convert(g));
        }
        pageDTO.setContent(goodsDTOList);

        return pageDTO;

    }
    public class MySpec implements Specification<Goods>{
        private Goods goods;
        public MySpec(Goods goods){
            this.goods=goods;
        }
        @Override
        public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            String goodsCode = goods.getGoodsCode();
            String goodsName = goods.getGoodsName();
            String goodsShelfLife = goods.getGoodsShelfLife();
            //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
            List<Predicate> predicates = new ArrayList<>();

            //对客户端查询条件进行判断,并封装Predicate断言对象
            // isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
            if (StringUtils.isNotBlank(goodsName)) {
                Predicate predicate = cb.like(root.get("goodsName").as(String.class), "%"+goodsName+"%");
                predicates.add(predicate);
            }
            if (StringUtils.isNotBlank(goodsCode)) {
                Predicate predicate = cb.like(root.get("goodsCode").as(String.class), "%"+goodsCode+"%");
                predicates.add(predicate);
            }
            if (StringUtils.isNotBlank(goodsShelfLife)) {
                Predicate predicate = cb.like(root.get("goodsShelfLife").as(String.class), "%"+goodsShelfLife+"%");
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
