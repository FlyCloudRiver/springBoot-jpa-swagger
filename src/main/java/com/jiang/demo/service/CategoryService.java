package com.jiang.demo.service;

import com.jiang.demo.entity.Category;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/4
 */
public interface CategoryService {

    Category insertCategory(Integer secondaryCategoryId, String categoryName);

    void deleteCategoryById(Integer id);

    Category updateCategory(Integer id,String categoryName,Integer secondaryCategoryId);

    List<Category> selectCategoryAll();

    Category selectCategoryById(Integer id);
}