package com.itheima.reggie.controller;

import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping(value = "/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse<String> addDish(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());
        return dishService.addDishWithFlavor(dishDto);
    }
}
