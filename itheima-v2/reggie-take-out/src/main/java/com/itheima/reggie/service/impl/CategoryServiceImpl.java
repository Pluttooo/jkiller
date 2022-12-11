package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseResponse<String> add(Category category) {
        categoryMapper.insert(category);
        return BaseResponse.success("新增成功");
    }

    @Override
    public BaseResponse<Page<Category>> getCategoryListByPage(int pageNum, int pageSize) {
        Page<Category> pageInfo = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.orderByDesc(Category::getUpdateTime);
        categoryMapper.selectPage(pageInfo, categoryLambdaQueryWrapper);
        return BaseResponse.success(pageInfo);
    }

    @Override
    public BaseResponse<String> updateCategoryById(Category category) {
        categoryMapper.updateById(category);
        return BaseResponse.success("更新成功");
    }

    @Override
    public BaseResponse<String> deleteCategoryById(Long id) {
        return null;
    }
}
