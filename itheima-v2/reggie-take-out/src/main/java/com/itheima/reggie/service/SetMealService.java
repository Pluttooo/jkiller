package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.dto.SetMealDto;

public interface SetMealService {

    BaseResponse<String> addSetMealWithDish(SetMealDto setMealDto);

    BaseResponse<Page<SetMealDto>> getSetMealByPage(int pageNum, int pageSize, String name);
}
