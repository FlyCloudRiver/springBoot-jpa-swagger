package com.jiang.demo.service.impl;

import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.entity.SecondaryCategory;
import com.jiang.demo.repository.BigCategoryRepository;
import com.jiang.demo.repository.SecondaryCategoryRepository;
import com.jiang.demo.service.SecondaryCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */

@Service
public class SecondaryCategoryServiceImpl implements SecondaryCategoryService {

    // 通过set方法注入
    private SecondaryCategoryRepository secondaryCategoryRepository;
    @Autowired
    public void setSecondaryCategoryRepository(SecondaryCategoryRepository secondaryCategoryRepository) {
        this.secondaryCategoryRepository = secondaryCategoryRepository;
    }

    private BigCategoryRepository bigCategoryRepository;
    @Autowired
    public void setBigCategoryRepository(BigCategoryRepository bigCategoryRepository) {
        this.bigCategoryRepository = bigCategoryRepository;
    }

    public SecondaryCategory insertSecondaryCategory(Integer bigCategoryId, String secondaryCategoryName){

        BigCategory bigCategory=bigCategoryRepository.findById(bigCategoryId).orElse(null);
        SecondaryCategory secondaryCategory=new SecondaryCategory();

        secondaryCategory.setBigCategory(bigCategory);
        secondaryCategory.setSecondaryCategoryName(secondaryCategoryName);

        return  secondaryCategoryRepository.save(secondaryCategory);
    }

    public void deleteSecondaryCategoryById(Integer id){
        secondaryCategoryRepository.deleteById(id);
    }

    public SecondaryCategory updateSecondaryCategory(Integer id,String secondaryCategoryName,Integer bigCategoryId){

        BigCategory bigCategory=bigCategoryRepository.findById(bigCategoryId).orElse(null);

        SecondaryCategory secondaryCategory=new SecondaryCategory();

        secondaryCategory.setSecondaryCategoryName(secondaryCategoryName);
        secondaryCategory.setId(id);
        secondaryCategory.setBigCategory(bigCategory);
        return secondaryCategoryRepository.save(secondaryCategory);
    }

    @Override
    public List<SecondaryCategory> selectSecondaryCategoryAll() {
        return secondaryCategoryRepository.findAll();
    }

   /* public Iterator<SecondaryCategory> selectSecondaryCategoryAll(Integer pageNum,Integer pageSize){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum,pageSize,sort);
        Page<SecondaryCategory> secondaryCategories = secondaryCategoryRepository.findAll(pageable);
        Iterator<SecondaryCategory> secondaryCategoryIterator =  secondaryCategories.iterator();

        *//*List<SecondaryCategory> all = SecondaryCategoryRepository.findAll();*//*
        return secondaryCategoryIterator;
    }*/

    public SecondaryCategory selectSecondaryCategoryById(Integer id){

        return secondaryCategoryRepository.findById(id).orElse(null);
    }
}
