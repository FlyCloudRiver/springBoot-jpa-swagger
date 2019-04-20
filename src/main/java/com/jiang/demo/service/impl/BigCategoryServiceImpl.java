package com.jiang.demo.service.impl;

import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.repository.BigCategoryRepository;
import com.jiang.demo.service.BigCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public BigCategory insertBigCategory(BigCategory bigCategory){
        return  bigCategoryRepository.save(bigCategory);
    }

    public void deleteBigCategoryById(Integer id){
        bigCategoryRepository.deleteById(id);
    }

    public BigCategory updateBigCategory(BigCategory bigCategory){
        return bigCategoryRepository.save(bigCategory);
    }

    public List<BigCategory> selectBigCategoryAll(){
        return bigCategoryRepository.findAll();
    }

    public BigCategory selectBigCategoryById(Integer id){
        return bigCategoryRepository.findById(id).orElse(null);
    }
}
