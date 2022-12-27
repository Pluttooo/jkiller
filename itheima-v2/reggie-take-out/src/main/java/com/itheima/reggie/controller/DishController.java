package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(value = "/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse<String> addDish(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());
        return dishService.addDishWithFlavor(dishDto);
    }

    /**
     * 分页查询菜品信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get_dish_page", method = RequestMethod.GET)
    public BaseResponse<Page<DishDto>> getDishPage(
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
            @RequestParam(value = "page_size", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "name", required = true) String dishName
    ) {
        return dishService.getDishByPage(pageNum, pageSize, dishName);
    }

    /**
     * 根据id查询菜品信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get_dish_by_id/{id}", method = RequestMethod.GET)
    public BaseResponse<DishDto> getDishById(@PathVariable(value = "id") Long id) {
        DishDto dishDto = dishService.getDishWithFlavorById(id);
        if (dishDto == null) {
            return BaseResponse.error("未查询到该信息");
        }
        return BaseResponse.success(dishDto);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<String> update(@RequestBody DishDto dishDto) {
        return dishService.updateDishWithFlavor(dishDto);
    }
}
