package com.jiang.demo.service;


import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.dto.category.CategoryForm;
import com.jiang.demo.utils.PageDTO;

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

    PageDTO<CategoryDTO> findByDynamicCases(CategoryForm categoryForm);
}
