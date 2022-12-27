package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.dto.DishDto;

public interface DishService {

    BaseResponse<String> addDishWithFlavor(DishDto dishDto);

    BaseResponse<Page<DishDto>> getDishByPage(int pageNum, int pageSize, String dishName);

    DishDto getDishWithFlavorById(Long id);

    BaseResponse<String> updateDishWithFlavor(DishDto dishDto);
}
