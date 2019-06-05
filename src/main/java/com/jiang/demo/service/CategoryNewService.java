package com.jiang.demo.service;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.dto.bigCategory.BigCategoryForm;
import com.jiang.demo.dto.newCategory.NewCategoryForm;

/**
 * Author: 江云飞
 * Date:   2019/6/3
 */
public interface CategoryNewService {

    BigCategoryDTO insertAllCategory(NewCategoryForm newCategoryForm);

    void updateAllCategory(BigCategoryDTO bigCategoryDTO);
}
