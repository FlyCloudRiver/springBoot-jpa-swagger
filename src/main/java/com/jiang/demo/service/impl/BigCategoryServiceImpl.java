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

    @Autowired
    private BigCategoryRepository bigCategoryRepository;

    public BigCategory insertBigCategory(BigCategory bigCategory){
        BigCategory save = bigCategoryRepository.saveAndFlush(bigCategory);
        return  save;
    }

    public void deleteBigCategoryById(Integer id){
        bigCategoryRepository.deleteById(id);
    }

    public BigCategory updateBigCategory(BigCategory bigCategory){
        BigCategory save = bigCategoryRepository.save(bigCategory);
        return save;
    }

    public List<BigCategory> selectBigCategoryAll(){
        List<BigCategory> all = bigCategoryRepository.findAll();
        return all;
    }

    public BigCategory selectBigCategoryById(Integer id){
        BigCategory bigCategory = bigCategoryRepository.findById(id).get();
        return bigCategory;
    }
}
