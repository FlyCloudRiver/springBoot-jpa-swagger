package com.jiang.demo.service.impl;

import com.jiang.demo.exception.MyException;
import com.jiang.demo.utils.PageDTO;
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
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Author: 江云飞
 * Date:   2019/4/6
 */

@Service
public class GoodsServiceImpl implements GoodsService {
    // 通过set方法注入
    private GoodsRepository goodsRepository;
    @Autowired
    public void setGoodsRepository(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    private CategoryRepository categoryRepository;
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private SupplierRepository supplierRepository;
    @Autowired
    public void setSupplierRepository(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }


    public  static int count=1;
    public GoodsDTO insertGoods(GoodsForm goodsForm){
        Goods goods=new Goods();
        /*将前者赋值给后者*/
        BeanUtils.copyProperties(goodsForm, goods);

        Category category = categoryRepository.findById(goodsForm.getCategoryId()).orElse(null);
        goods.setCategory(category);

        Supplier supplier=supplierRepository.findById(goodsForm.getSupplierId()).orElse(null);
        goods.setSupplier(supplier);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(date);

        String goodsCode=goodsForm.getCategoryId()+goodsForm.getSupplierId()+dateString+count;
        if(count<=10000){
            count++;
        }else{
            count=0;
        }
        goods.setGoodsCode(goodsCode);
        Goods save = goodsRepository.save(goods);

        /*传进去实体类 返回 DTO类*/
        return  GoodsDTO.convert(save);
    }

    public GoodsDTO findGoodsDTOById(Integer id){
        Goods goods = goodsRepository.findById(id).orElse(null);
        /*传进去实体类 返回 DTO类*/
        return  GoodsDTO.convert(goods);
    }

    @Override
    public GoodsDTO updateGoods(GoodsDTO goodsDTO) {
        /*将前者赋值给后者*/
        Goods goods = goodsRepository.findById(goodsDTO.getId()).orElse(null);
        if(goods!=null){
            BeanUtils.copyProperties(goodsDTO, goods);
        }
        Integer categoryId = goodsDTO.getCategoryId();
        Integer supplierId = goodsDTO.getSupplierId();
        if(categoryId==null||categoryId==0){
            throw new MyException(-1,"类别不能为空");
        }
        if(supplierId==null||supplierId==0){
            throw new MyException(-1,"厂商不能为空");
        }

        Category category = categoryRepository.findById(categoryId).orElse(null);
        Supplier supplier = supplierRepository.findById(supplierId).orElse(null);

        if(category!=null){
            goods.setCategory(category);
        }else{
            throw new MyException(-2,"该类不存在");
        }

        if(supplier!=null){
            goods.setSupplier(supplier);
        }else{
            throw new MyException(-2,"该厂商不存在");
        }


        return GoodsDTO.convert(goodsRepository.save(goods));
    }

    @Override
    public void deleteGoods(Integer id) {
        try{
            goodsRepository.deleteById(id);
        }catch (Exception e){
            throw  new MyException(-1,"该商品已经入库，不能删除");
        }

    }


    //动态查询:
    public PageDTO<GoodsDTO> findByDynamicCases(GoodsForm goodsForm){

        Integer pageNum=goodsForm.getPageNum();
        Integer pageSize=goodsForm.getPageSize();
        //新建商品类  将form转换成goods
        Goods goods=new Goods();
        BeanUtils.copyProperties(goodsForm, goods);
        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Page<Goods> gooodsies = goodsRepository.findAll(new MySpec(goodsForm),pageable);
        //封装分页
        PageDTO<GoodsDTO> pageDTO =new PageDTO<>();
        BeanUtils.copyProperties(gooodsies, pageDTO);
        List<Goods> content = gooodsies.getContent();
        List<GoodsDTO> goodsDTOList=new ArrayList<>();
        for (Goods g:content) {
            goodsDTOList.add(GoodsDTO.convert(g));
        }
        pageDTO.setContent(goodsDTOList);

        return pageDTO;

    }
    private class MySpec implements Specification<Goods>{
        private GoodsForm goodsForm;
        private MySpec(GoodsForm goodsForm){
            this.goodsForm=goodsForm;
        }
        @Override
        public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            String goodsCode = goodsForm.getGoodsCode();
            String goodsName = goodsForm.getGoodsName();
            //类别名 厂商名
            String categoryName = goodsForm.getCategoryName();
            String supplierName = goodsForm.getSupplierName();

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(categoryName)){
                //Predicate predicate = cb.like(root.get(storeroom.getGoods().getGoodsCode()),"%"+storeroomForm.getGoodsCode()+"%");
                //ListJoin<Storeroom,Goods> join=root.join(root.getModel().getList("goods",Goods.class));
                Join<Goods,Category> join=root.join("category");
                Predicate predicate = cb.like(join.get("categoryName").as(String.class),"%"+categoryName+"%");
                predicates.add(predicate);
            }
            if (StringUtils.isNotBlank(supplierName)){

                Join<Goods,Supplier> join=root.join("supplier");
                Predicate predicate = cb.like(join.get("supplierName").as(String.class),"%"+supplierName+"%");
                predicates.add(predicate);
            }
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
