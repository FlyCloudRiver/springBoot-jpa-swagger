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
    public CategoryDTO insertCategory(Integer secondaryCategoryId, String categoryName) {

        SecondaryCategory secondaryCategory = secondaryCategoryRepository.findById(secondaryCategoryId).orElse(null);

        Category category=new Category();
        category.setSecondaryCategory(secondaryCategory);
        category.setCategoryName(categoryName);
        return CategoryDTO.convert(categoryRepository.save(category));
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO updateCategory(Integer id, String categoryName, Integer secondaryCategoryId) {
        SecondaryCategory secondaryCategory = secondaryCategoryRepository.findById(secondaryCategoryId).orElse(null);

        Category category =new Category();
        category.setId(id);
        category.setCategoryName(categoryName);
        category.setSecondaryCategory(secondaryCategory);

        return CategoryDTO.convert(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> selectCategoryAll() {
        List<Category> all = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category c:all) {
            CategoryDTO convert = CategoryDTO.convert(c);
            categoryDTOList.add(convert);
        }
        return categoryDTOList;
    }

    @Override
    public CategoryDTO selectCategoryById(Integer id) {
        return CategoryDTO.convert(categoryRepository.findById(id).orElse(null));
    }



}
