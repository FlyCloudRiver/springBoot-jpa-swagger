package com.jiang.demo.service.impl;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.BigCategoryRepository;
import com.jiang.demo.service.BigCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
