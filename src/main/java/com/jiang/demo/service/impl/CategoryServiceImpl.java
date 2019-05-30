package com.jiang.demo.service.impl;


import com.jiang.demo.dto.category.CategoryDTO;
import com.jiang.demo.dto.category.CategoryForm;
import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.dto.goods.GoodsForm;
import com.jiang.demo.entity.Category;
import com.jiang.demo.entity.Goods;
import com.jiang.demo.entity.SecondaryCategory;
import com.jiang.demo.entity.Supplier;
import com.jiang.demo.exception.MyException;
import com.jiang.demo.repository.CategoryRepository;
import com.jiang.demo.repository.SecondaryCategoryRepository;
import com.jiang.demo.service.CategoryService;
import com.jiang.demo.utils.PageDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
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
        try{
            categoryRepository.deleteById(id);
        }catch (Exception e){
            throw new MyException(-1,"关联商品已入库，不能删除");
        }

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

    @Override
    public PageDTO<CategoryDTO> findByDynamicCases(CategoryForm categoryForm) {
        Integer pageNum=categoryForm.getPageNum();
        Integer pageSize=categoryForm.getPageSize();
        //新建商品类  将form转换成goods
        Category category=new Category();
        BeanUtils.copyProperties(categoryForm, category);
        //分页插件
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize,sort);
        Page<Category> categories = categoryRepository.findAll(new MySpec(categoryForm),pageable);
        //封装分页
        PageDTO<CategoryDTO> pageDTO =new PageDTO<>();
        BeanUtils.copyProperties(categories, pageDTO);
        List<Category> content = categories.getContent();
        List<CategoryDTO> categoryDTOList=new ArrayList<>();
        for (Category g:content) {
            categoryDTOList.add(CategoryDTO.convert(g));
        }
        pageDTO.setContent(categoryDTOList);

        return pageDTO;
    }

    @Override
    public List<CategoryDTO> selectCategoryBySecondId(Integer secondId) {
        List<Category> categories = categoryRepository.selectCategoryBySecondId(secondId);
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categories) {
            categoryDTOList.add(CategoryDTO.convert(category));
        }
        return categoryDTOList;

    }

    private class MySpec implements Specification<Category> {
        private CategoryForm categoryForm;

        private MySpec(CategoryForm categoryForm) {
            this.categoryForm = categoryForm;
        }

        @Override
        public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            String categoryName = categoryForm.getCategoryName();

            //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
            List<Predicate> predicates = new ArrayList<>();

            //对客户端查询条件进行判断,并封装Predicate断言对象
            // isNotBlank(str) 等价于 str != null && str.length > 0 && str.trim().length > 0
            if (StringUtils.isNotBlank(categoryName)) {
                Predicate predicate = cb.like(root.get("categoryName").as(String.class), "%" + categoryName + "%");
                predicates.add(predicate);
            }

            //判断结合中是否有数据
            if (predicates.size() == 0) {
                return null;
            }

            //将集合转化为CriteriaBuilder所需要的Predicate[]
            Predicate[] predicateArr = new Predicate[predicates.size()];
            predicateArr = predicates.toArray(predicateArr);

            // 返回所有获取的条件： 条件 or 条件 or 条件 or 条件
            return cb.and(predicateArr);
            //return cb.or(predicateArr);

        }


    }    }
