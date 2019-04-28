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

    Result<BigCategoryDTO> insertBigCategory(BigCategory bigCategory);

    Result deleteBigCategoryById(Integer id);

    Result<BigCategoryDTO> updateBigCategory(Integer id,String bigCategoryName);

    Result<List<BigCategoryDTO>> selectBigCategoryAll();

    Result<BigCategoryDTO> selectBigCategoryById(Integer id) throws Exception;
}
