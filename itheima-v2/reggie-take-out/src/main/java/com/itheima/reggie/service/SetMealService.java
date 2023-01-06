package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.dto.SetMealDto;
import com.itheima.reggie.entity.SetMeal;

import java.util.List;

public interface SetMealService {

    BaseResponse<String> addSetMealWithDish(SetMealDto setMealDto);

    BaseResponse<Page<SetMealDto>> getSetMealByPage(int pageNum, int pageSize, String name);

    BaseResponse<String> deleteSetMealWithDishById(List<Long> ids);

    BaseResponse<List<SetMeal>> querySetMealLsitByCondition(SetMeal setMeal);
}
