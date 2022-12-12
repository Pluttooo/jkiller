package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.Category;

import java.util.List;

public interface CategoryService {

    BaseResponse<String> add(Category category);

    BaseResponse<Page<Category>> getCategoryListByPage(int pageNum, int pageSize);

    BaseResponse<String> updateCategoryById(Category category);

    BaseResponse<String> deleteCategoryById(Long id);

    List<Category> queryCategoryListByCondition(Category category);
}
