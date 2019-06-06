package com.jiang.demo.service.impl;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.dto.bigCategory.BigCategoryForm;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.BigCategoryRepository;
import com.jiang.demo.service.BigCategoryService;
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

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;



/**
 * Author: 江云飞
 * Date:   2019/4/1
 */

@Service
public class BigCategoryServiceImpl implements BigCategoryService {

    // 通过set方法注入  优先选择
    private BigCategoryRepository bigCategoryRepository;
    @Autowired
    public void setBigCategoryRepository(BigCategoryRepository bigCategoryRepository) {
        this.bigCategoryRepository = bigCategoryRepository;
    }


    public BigCategoryDTO insertBigCategory(BigCategory bigCategory){
        return BigCategoryDTO.convert(bigCategoryRepository.save(bigCategory)) ;
    }

    public void deleteBigCategoryById(Integer id){
        try{
            bigCategoryRepository.deleteById(id);
        }catch (Exception e){
            throw new MyException(-1,"关联商品已入库，不能删除");
        }


    }

    public BigCategoryDTO updateBigCategory(Integer id,String bigCategoryName){
        BigCategory bigCategory=new BigCategory();
        bigCategory.setBigCategoryName(bigCategoryName);
        bigCategory.setId(id);
        return BigCategoryDTO.convert(bigCategoryRepository.save(bigCategory));
    }

    public List<BigCategoryDTO> selectBigCategoryAll(){
        List<BigCategory> all = bigCategoryRepository.findAll();
        List<BigCategoryDTO> bigCategoryDTOs =new ArrayList<>();

        for (BigCategory b:all) {
            BigCategoryDTO convert = BigCategoryDTO.convert(b);
            bigCategoryDTOs.add(convert);
        }
        return bigCategoryDTOs;
    }


    public BigCategoryDTO selectBigCategoryById(Integer id){
        return BigCategoryDTO.convert(bigCategoryRepository.findById(id).orElse(null)) ;
    }

    @Override
    public PageDTO<BigCategoryDTO> selectDynamicCases(BigCategoryForm bigCategoryForm) {

        Integer pageNum = bigCategoryForm.getPageNum();
        Integer pageSize = bigCategoryForm.getPageSize();
        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Page<BigCategory> bigCategoryPage = bigCategoryRepository.findAll(new MySpec(bigCategoryForm),pageable);

        //封装分页
        //基础字段赋值
        PageDTO<BigCategoryDTO> pageDTO =new PageDTO<>();
        BeanUtils.copyProperties(bigCategoryPage, pageDTO);
        //类  赋值
        List<BigCategory> content = bigCategoryPage.getContent();
        List<BigCategoryDTO> bigCategoryDTOList=new ArrayList<>();
        for (BigCategory g:content) {
            bigCategoryDTOList.add(BigCategoryDTO.convert(g));
        }
        pageDTO.setContent(bigCategoryDTOList);
        return pageDTO;
    }

    private class MySpec implements Specification<BigCategory> {
        private BigCategoryForm bigCategoryForm;

        private MySpec(BigCategoryForm bigCategoryForm) {
            this.bigCategoryForm = bigCategoryForm;
        }

        @Override
        public Predicate toPredicate(Root<BigCategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            String bigCategoryName = bigCategoryForm.getBigCategoryName();

            //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
            List<Predicate> predicates = new ArrayList<>();

            //对客户端查询条件进行判断,并封装Predicate断言对象
            // isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
            if (StringUtils.isNotBlank(bigCategoryName)) {
                Predicate predicate = cb.like(root.get("bigCategoryName").as(String.class), "%" + bigCategoryName + "%");
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
