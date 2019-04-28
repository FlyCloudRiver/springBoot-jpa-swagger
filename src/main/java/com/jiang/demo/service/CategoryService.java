package com.jiang.demo.service;


import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.entity.Category;
import com.jiang.demo.utils.Result;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/4
 */
public interface CategoryService {

    Result<CategoryDTO> insertCategory(Integer secondaryCategoryId, String categoryName);

    Result deleteCategoryById(Integer id);

    Result<CategoryDTO> updateCategory(Integer id,String categoryName,Integer secondaryCategoryId);

    Result<List<CategoryDTO>> selectCategoryAll();

    Result<CategoryDTO> selectCategoryById(Integer id);

    //PageDTO<CategoryDTO> findByDynamicCases(Integer bigCategoryId, Integer secondaryCategoryId, Integer pageNum, Integer pageSize);
}
