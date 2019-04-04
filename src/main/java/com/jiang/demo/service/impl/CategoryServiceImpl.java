package com.jiang.demo.service.impl;

import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.SecondaryCategory;
import com.jiang.demo.repository.CategoryRepository;
import com.jiang.demo.repository.SecondaryCategoryRepository;
import com.jiang.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/4
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SecondaryCategoryRepository secondaryCategoryRepository;
    @Override
    public Category insertCategory(Integer secondaryCategoryId, String categoryName) {

        SecondaryCategory secondaryCategory = secondaryCategoryRepository.findById(secondaryCategoryId).get();

        Category category=new Category();
        category.setSecondaryCategory(secondaryCategory);
        category.setCategoryName(categoryName);

        Category save = categoryRepository.save(category);
        return save;
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategory(Integer id, String categoryName, Integer secondaryCategoryId) {
        SecondaryCategory secondaryCategory = secondaryCategoryRepository.findById(secondaryCategoryId).get();

        Category category =new Category();
        category.setId(id);
        category.setCategoryName(categoryName);
        category.setSecondaryCategory(secondaryCategory);

        Category save = categoryRepository.save(category);
        return save;
    }

    @Override
    public List<Category> selectCategoryAll() {
        List<Category> all = categoryRepository.findAll();
        return all;
    }

    @Override
    public Category selectCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).get();
        return category;
    }
}
