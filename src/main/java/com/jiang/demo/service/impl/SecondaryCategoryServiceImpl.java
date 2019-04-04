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


    @Autowired
    private SecondaryCategoryRepository SecondaryCategoryRepository;

    @Autowired
    private BigCategoryRepository bigCategoryRepository;

    public SecondaryCategory insertSecondaryCategory(Integer bigCategoryId,String secondaryCategoryName){

        BigCategory bigCategory=bigCategoryRepository.findById(bigCategoryId).get();
        SecondaryCategory secondaryCategory=new SecondaryCategory();

        secondaryCategory.setBigCategory(bigCategory);
        secondaryCategory.setSecondaryCategoryName(secondaryCategoryName);
        SecondaryCategory save = SecondaryCategoryRepository.save(secondaryCategory);
        return  save;
    }

    public void deleteSecondaryCategoryById(Integer id){
        SecondaryCategoryRepository.deleteById(id);
    }

    public SecondaryCategory updateSecondaryCategory(Integer id,String secondaryCategoryName,Integer bigCategoryId){

        BigCategory bigCategory=bigCategoryRepository.findById(bigCategoryId).get();

        SecondaryCategory secondaryCategory=new SecondaryCategory();

        secondaryCategory.setSecondaryCategoryName(secondaryCategoryName);
        secondaryCategory.setId(id);
        secondaryCategory.setBigCategory(bigCategory);

        SecondaryCategory save = SecondaryCategoryRepository.save(secondaryCategory);
        return save;
    }

    @Override
    public List<SecondaryCategory> selectSecondaryCategoryAll() {
        List<SecondaryCategory> all = SecondaryCategoryRepository.findAll();
        return all;
    }

   /* public Iterator<SecondaryCategory> selectSecondaryCategoryAll(Integer pageNum,Integer pageSize){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum,pageSize,sort);
        Page<SecondaryCategory> secondaryCategories = SecondaryCategoryRepository.findAll(pageable);
        Iterator<SecondaryCategory> secondaryCategoryIterator =  secondaryCategories.iterator();

        *//*List<SecondaryCategory> all = SecondaryCategoryRepository.findAll();*//*
        return secondaryCategoryIterator;
    }*/

    public SecondaryCategory selectSecondaryCategoryById(Integer id){
        SecondaryCategory SecondaryCategory = SecondaryCategoryRepository.findById(id).get();
        return SecondaryCategory;
    }
}
