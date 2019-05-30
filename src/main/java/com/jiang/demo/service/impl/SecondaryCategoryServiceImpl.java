package com.jiang.demo.service.impl;

import com.jiang.demo.dto.secondaryCategory.SecondaryCategoryDTO;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.entity.SecondaryCategory;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.BigCategoryRepository;
import com.jiang.demo.repository.SecondaryCategoryRepository;
import com.jiang.demo.service.SecondaryCategoryService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        try{
            secondaryCategoryRepository.deleteById(id);
        }catch (Exception e){
            throw new MyException(-1,"关联商品已入库，不能删除");
        }

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


    public SecondaryCategory selectSecondaryCategoryById(Integer id){

        return secondaryCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<SecondaryCategoryDTO> selectSecondaryCategoryByBigId(Integer bigCategoryId) {
        List<SecondaryCategory> secondaryCategoryList = secondaryCategoryRepository.selectCategoryByBigId(bigCategoryId);
        List<SecondaryCategoryDTO> secondaryCategoryDTOList = new ArrayList<>();
        for (SecondaryCategory secondaryCategory : secondaryCategoryList) {
            secondaryCategoryDTOList.add(SecondaryCategoryDTO.convert(secondaryCategory));
        }
        return secondaryCategoryDTOList;
    }
}
