package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.mapper.DishFlavorMapper;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseResponse<String> addDishWithFlavor(DishDto dishDto) {
        try {
            dishMapper.insert(dishDto);
            Long dishId = dishDto.getId();
            List<DishFlavor> flavors = dishDto.getFlavors();
            flavors = flavors.stream().peek(item -> item.setDishId(dishId)).collect(Collectors.toList());
            dishFlavorMapper.insertBatchSomeColumn(flavors);
            return BaseResponse.success("新增成功");
        } catch (Exception e) {
            return BaseResponse.error(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Page<DishDto>> getDishByPage(int pageNum, int pageSize, String dishName) {
        Page<Dish> dishPageInfo = new Page<>();
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.like(dishName != null, Dish::getName, dishName);
        dishLambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);
        dishMapper.selectPage(dishPageInfo, dishLambdaQueryWrapper);

        Page<DishDto> dishDtoPageInfo = new Page<>();
        BeanUtils.copyProperties(dishPageInfo, dishDtoPageInfo, "records");
        List<Dish> dishPageInfoRecords = dishPageInfo.getRecords();
        List<DishDto> dishDtoList = dishPageInfoRecords.stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryMapper.selectById(categoryId);
            if (category != null) {
                dishDto.setCategoryName(category.getName());
            }
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPageInfo.setRecords(dishDtoList);

        return BaseResponse.success(dishDtoPageInfo);
    }

    @Override
    public DishDto getDishWithFlavorById(Long id) {
        Dish dish = dishMapper.selectById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> dishFlavors = dishFlavorMapper.selectList(dishFlavorLambdaQueryWrapper);
        dishDto.setFlavors(dishFlavors);
        return dishDto;
    }

    @Override
    public BaseResponse<String> updateDishWithFlavor(DishDto dishDto) {
        try {
            dishMapper.updateById(dishDto);

            LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
            dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
            dishFlavorMapper.delete(dishFlavorLambdaQueryWrapper);

            List<DishFlavor> dishFlavors = dishDto.getFlavors();
            dishFlavors = dishFlavors.stream().peek(item -> item.setDishId(dishDto.getId())).collect(Collectors.toList());
            dishFlavorMapper.insertBatchSomeColumn(dishFlavors);
            return BaseResponse.success("更新成功");
        } catch (Exception e) {
            return BaseResponse.error(e.getMessage());
        }
    }
}
