package com.jiang.demo.service.impl;

import com.jiang.demo.config.PageDTO;
import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.Goods;
import com.jiang.demo.entity.SecondaryCategory;
import com.jiang.demo.repository.CategoryRepository;
import com.jiang.demo.repository.SecondaryCategoryRepository;
import com.jiang.demo.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    /*根据大类id  中类id 动态查询细类*/
    @Override
    public PageDTO<CategoryDTO> findByDynamicCases(Integer bigCategoryId, Integer secondaryCategoryId, Integer pageNum, Integer pageSize) {

        Category category =new Category();
        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);

        Page<Category> gooodsies = categoryRepository.findAll(pageable);

        //封装分页
        PageDTO<GoodsDTO> pageDTO =new PageDTO<>();
        BeanUtils.copyProperties(gooodsies, pageDTO);
        return null;
    }
}
