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
    public Category insertCategory(Integer secondaryCategoryId, String categoryName) {

        SecondaryCategory secondaryCategory = secondaryCategoryRepository.findById(secondaryCategoryId).orElse(null);

        Category category=new Category();
        category.setSecondaryCategory(secondaryCategory);
        category.setCategoryName(categoryName);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category updateCategory(Integer id, String categoryName, Integer secondaryCategoryId) {
        SecondaryCategory secondaryCategory = secondaryCategoryRepository.findById(secondaryCategoryId).orElse(null);

        Category category =new Category();
        category.setId(id);
        category.setCategoryName(categoryName);
        category.setSecondaryCategory(secondaryCategory);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> selectCategoryAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category selectCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }


    /*根据大类id  中类id 动态查询细类*/
   /* @Override
    public PageDTO<CategoryDTO> findByDynamicCases(Integer bigCategoryId, Integer secondaryCategoryId, Integer pageNum, Integer pageSize) {

        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);

        Page<Category> gooodsies = categoryRepository.findAll(pageable);

        //封装分页
        PageDTO<GoodsDTO> pageDTO =new PageDTO<>();
        BeanUtils.copyProperties(gooodsies, pageDTO);
        return null;
    }*/
}
