package com.jiang.demo.service.impl;

import com.jiang.demo.dto.bigCategory.BigCategoryDTO;
import com.jiang.demo.dto.bigCategory.BigCategoryForm;
import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.dto.category.CategoryForm;
import com.jiang.demo.dto.newCategory.NewCategoryForm;
import com.jiang.demo.dto.secondaryCategory.SecondaryCategoryDTO;
import com.jiang.demo.dto.secondaryCategory.SecondaryCategoryForm;
import com.jiang.demo.entity.BigCategory;
import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.SecondaryCategory;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.BigCategoryRepository;
import com.jiang.demo.repository.CategoryRepository;
import com.jiang.demo.repository.SecondaryCategoryRepository;
import com.jiang.demo.service.CategoryNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/6/3
 */

@Service
public class CategoryNewServiceImpl implements CategoryNewService {

    private BigCategoryRepository bigCategoryRepository;
    @Autowired
    public void setBigCategoryRepository(BigCategoryRepository bigCategoryRepository) {
        this.bigCategoryRepository = bigCategoryRepository;
    }

    private SecondaryCategoryRepository secondaryCategoryRepository;
    @Autowired
    public void setSecondaryCategoryRepository(SecondaryCategoryRepository secondaryCategoryRepository) {
        this.secondaryCategoryRepository = secondaryCategoryRepository;
    }

    private CategoryRepository categoryRepository;
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public BigCategoryDTO insertAllCategory(NewCategoryForm newCategoryForm) {
        //1.获取大类
        BigCategory bigCategory = bigCategoryRepository.findById(newCategoryForm.getBigId()).orElse(null);

        List<SecondaryCategory> secondaryCategoryList = new ArrayList<>();
        for (SecondaryCategoryForm secondaryCategoryForm : newCategoryForm.getSecondaryCategoryForms()) {
            //2.创建中类 持久化
            SecondaryCategory secondaryCategory = new SecondaryCategory();
            secondaryCategory.setSecondaryCategoryName(secondaryCategoryForm.getSecondaryCategoryName());
            secondaryCategory.setBigCategory(bigCategory);
            SecondaryCategory secondaryCategory2 = secondaryCategoryRepository.save(secondaryCategory);

            //如果细类存在
            if(secondaryCategoryForm.getCategoryForms().size()!=0){
                List<Category> categories = new ArrayList<>();
                for (CategoryForm categoryForm : secondaryCategoryForm.getCategoryForms()) {
                //3.创建细类
                  String categoryName = categoryForm.getCategoryName();
                 Category category = new Category();
                 category.setCategoryName(categoryName);
                 category.setSecondaryCategory(secondaryCategory2);

                 Category save = categoryRepository.save(category);
                 categories.add(save);
               }
                  secondaryCategory2.setCategoryList(categories);
                  SecondaryCategory save1 = secondaryCategoryRepository.save(secondaryCategory2);
                  secondaryCategoryList.add(save1);
            }else{
                secondaryCategoryList.add(secondaryCategory2);
            }

        }
        bigCategory.setSecondaryCategoryList(secondaryCategoryList);
        BigCategory bigCategory3 = bigCategoryRepository.save(bigCategory);

        return BigCategoryDTO.convert(bigCategory3);
    }

    @Override
    @Transactional
    public void updateAllCategory(BigCategoryDTO bigCategoryDTO) {
        //根据ID 查询次大类下面所有类别
            //获取中类
            List<SecondaryCategoryDTO> secondaryCategoryDTOS = bigCategoryDTO.getSecondaryCategoryDTOS();
            for (SecondaryCategoryDTO secondaryCategoryDTO : secondaryCategoryDTOS) {
                Integer secondaryId = secondaryCategoryDTO.getId();
                SecondaryCategory secondaryCategory = secondaryCategoryRepository.findById(secondaryId).orElse(null);
                if (secondaryCategory != null) {
                    secondaryCategory.setSecondaryCategoryName(secondaryCategoryDTO.getSecondaryCategoryName());
                    //更新
                    secondaryCategoryRepository.save(secondaryCategory);
                }else {
                    throw  new MyException(-1,"该中类不存在");
                }
                List<CategoryDTO> categoryDTOS = secondaryCategoryDTO.getCategoryDTOS();
                for (CategoryDTO categoryDTO : categoryDTOS) {
                    Category category = categoryRepository.findById(categoryDTO.getId()).orElse(null);
                    if (category != null) {
                        category.setCategoryName(categoryDTO.getCategoryName());
                        categoryRepository.save(category);
                    }else{
                        throw  new MyException(-1,"该细类不存在");
                    }
                }
            }

        }
}
