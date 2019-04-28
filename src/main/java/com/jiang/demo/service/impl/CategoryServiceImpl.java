package com.jiang.demo.service.impl;


import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.SecondaryCategory;
import com.jiang.demo.repository.CategoryRepository;
import com.jiang.demo.repository.SecondaryCategoryRepository;
import com.jiang.demo.service.CategoryService;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/4
 */

@Service
public class CategoryServiceImpl implements CategoryService {
    // 通过set方法注入
    private CategoryRepository categoryRepository;
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private SecondaryCategoryRepository secondaryCategoryRepository;
    @Autowired
    public void setSecondaryCategoryRepository(SecondaryCategoryRepository secondaryCategoryRepository) {
        this.secondaryCategoryRepository = secondaryCategoryRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Result<CategoryDTO> insertCategory(Integer secondaryCategoryId, String categoryName) {

        SecondaryCategory secondaryCategory = secondaryCategoryRepository.findById(secondaryCategoryId).orElse(null);

        Category category=new Category();
        category.setSecondaryCategory(secondaryCategory);
        category.setCategoryName(categoryName);
        return ResultUtil.success(CategoryDTO.convert(categoryRepository.save(category)));
    }

    @Override
    public Result deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
        return ResultUtil.success();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Result<CategoryDTO> updateCategory(Integer id, String categoryName, Integer secondaryCategoryId) {
        SecondaryCategory secondaryCategory = secondaryCategoryRepository.findById(secondaryCategoryId).orElse(null);

        Category category =new Category();
        category.setId(id);
        category.setCategoryName(categoryName);
        category.setSecondaryCategory(secondaryCategory);

        return ResultUtil.success(CategoryDTO.convert(categoryRepository.save(category)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Result<List<CategoryDTO>> selectCategoryAll() {
        List<Category> all = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category c:all) {
            CategoryDTO convert = CategoryDTO.convert(c);
            categoryDTOList.add(convert);
        }
        return ResultUtil.success(categoryDTOList);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Result<CategoryDTO> selectCategoryById(Integer id) {
        return ResultUtil.success(CategoryDTO.convert(categoryRepository.findById(id).orElse(null)));
    }



}
