package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.dto.SetMealDto;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.SetMeal;
import com.itheima.reggie.entity.SetMealDish;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.mapper.SetMealDishMapper;
import com.itheima.reggie.mapper.SetMealMapper;
import com.itheima.reggie.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class SetMealServiceImpl implements SetMealService {

    @Autowired
    private SetMealMapper setMealMapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseResponse<String> addSetMealWithDish(SetMealDto setMealDto) {
        try {
            setMealMapper.insert(setMealDto);
            List<SetMealDish> setMealDishes = setMealDto.getSetMealDishes();
            setMealDishes = setMealDishes.stream().peek(item -> item.setSetMealId(setMealDto.getId())).collect(Collectors.toList());
            setMealDishMapper.insertBatchSomeColumn(setMealDishes);
            return BaseResponse.success("新增成功");
        } catch (Exception e) {
            return BaseResponse.error(e.getMessage());
        }
    }

    @Override
    public BaseResponse<Page<SetMealDto>> getSetMealByPage(int pageNum, int pageSize, String name) {
        Page<SetMeal> setMealPageInfo = new Page<>();
        LambdaQueryWrapper<SetMeal> setMealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setMealLambdaQueryWrapper.like(name != null, SetMeal::getName, name);
        setMealLambdaQueryWrapper.orderByDesc(SetMeal::getUpdateTime);
        setMealMapper.selectPage(setMealPageInfo, setMealLambdaQueryWrapper);

        Page<SetMealDto> setMealDtoPageInfo = new Page<>();
        BeanUtils.copyProperties(setMealPageInfo, setMealDtoPageInfo, "records");
        List<SetMeal> setMealList = setMealPageInfo.getRecords();
        List<SetMealDto> setMealDtoList = setMealList.stream().map(setMeal -> {
            SetMealDto setMealDto = new SetMealDto();
            BeanUtils.copyProperties(setMeal, setMealDto);
            Long categoryId = setMeal.getCategoryId();
            Category category = categoryMapper.selectById(categoryId);
            setMealDto.setCategoryName(category.getName());
            return setMealDto;
        }).collect(Collectors.toList());
        setMealDtoPageInfo.setRecords(setMealDtoList);

        return BaseResponse.success(setMealDtoPageInfo);
    }

    @Override
    public BaseResponse<String> deleteSetMealWithDishById(List<Long> ids) {
        LambdaQueryWrapper<SetMeal> setMealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setMealLambdaQueryWrapper.in(SetMeal::getId, ids);
        setMealLambdaQueryWrapper.eq(SetMeal::getStatus, 1);

        int count = setMealMapper.selectCount(setMealLambdaQueryWrapper);
        if (count > 0) {
            throw new CustomException("套餐正在售卖中，不能删除");
        }

        setMealMapper.deleteBatchIds(ids);
        LambdaQueryWrapper<SetMealDish> setMealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setMealDishLambdaQueryWrapper.in(SetMealDish::getDishId, ids);
        setMealDishMapper.deleteBatchIds(ids);
        return BaseResponse.success("套餐删除成功！");
    }

    @Override
    public BaseResponse<List<SetMeal>> querySetMealLsitByCondition(SetMeal setMeal) {
        LambdaQueryWrapper<SetMeal> setMealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setMealLambdaQueryWrapper.eq(setMeal.getCategoryId() != null, SetMeal::getCategoryId, setMeal.getCategoryId());
        setMealLambdaQueryWrapper.eq(setMeal.getStatus() != null, SetMeal::getStatus, setMeal.getStatus());
        setMealLambdaQueryWrapper.orderByDesc(SetMeal::getUpdateTime);
        List<SetMeal> setMealList = setMealMapper.selectList(setMealLambdaQueryWrapper);
        if (CollectionUtils.isEmpty(setMealList)) {
            BaseResponse.error("未查询到该套餐信息");
        }
        return BaseResponse.success(setMealList);
    }
}
