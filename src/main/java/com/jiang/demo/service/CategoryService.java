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

    CategoryDTO insertCategory(Integer secondaryCategoryId, String categoryName);

    void deleteCategoryById(Integer id);

    CategoryDTO updateCategory(Integer id,String categoryName,Integer secondaryCategoryId);

    List<CategoryDTO> selectCategoryAll();

    CategoryDTO selectCategoryById(Integer id);

    //PageDTO<CategoryDTO> findByDynamicCases(Integer bigCategoryId, Integer secondaryCategoryId, Integer pageNum, Integer pageSize);
}
