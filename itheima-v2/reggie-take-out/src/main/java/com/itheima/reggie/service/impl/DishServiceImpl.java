package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.DishFlavor;
import com.itheima.reggie.mapper.DishFlavorMapper;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Override
    public BaseResponse<String> addDishWithFlavor(DishDto dishDto) {
        dishMapper.insert(dishDto);
        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map(item -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        dishFlavorMapper.insertBatchSomeColumn(flavors);
        return BaseResponse.success("新增成功");
    }

    @Override
    public BaseResponse<Page<DishDto>> getDishByPage(int pageNum, int pageSize) {
        return null;
    }
}
