package com.jiang.demo.service;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.utils.Result;


import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/1
 */


public interface BigCategoryService {

    BigCategoryDTO insertBigCategory(BigCategory bigCategory);

    void deleteBigCategoryById(Integer id);

    BigCategoryDTO updateBigCategory(Integer id,String bigCategoryName);

    List<BigCategoryDTO> selectBigCategoryAll();

    BigCategoryDTO selectBigCategoryById(Integer id) throws Exception;
}
