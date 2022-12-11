package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.SetMeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.mapper.SetMealMapper;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetMealMapper setMealMapper;

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
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        Integer dishCount = dishMapper.selectCount(dishLambdaQueryWrapper);
        if (dishCount > 0) {
            throw new CustomException("当前分类关联了菜品，不能删除");
        }
        LambdaQueryWrapper<SetMeal> setMealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setMealLambdaQueryWrapper.eq(SetMeal::getCategoryId, id);
        Integer setMealCount = setMealMapper.selectCount(setMealLambdaQueryWrapper);
        if (setMealCount > 0) {
            throw new CustomException("当前分类关联了套餐，不能删除");
        }
        categoryMapper.deleteById(id);
        return BaseResponse.success("分类删除成功");
    }
}
