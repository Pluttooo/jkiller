package com.itheima.reggie.service;

import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.dto.DishDto;

public interface DishService {

    BaseResponse<String> addDishWithFlavor(DishDto dishDto);
}
