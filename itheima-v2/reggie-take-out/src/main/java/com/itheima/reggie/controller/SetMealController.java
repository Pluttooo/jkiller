package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.dto.SetMealDto;
import com.itheima.reggie.entity.SetMeal;
import com.itheima.reggie.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/api/set_meal")
public class SetMealController {

    @Autowired
    private SetMealService setMealService;

    /**
     * 新增套餐
     * @param setMealDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse<String> addSetMealWithDish(@RequestBody SetMealDto setMealDto) {
        return setMealService.addSetMealWithDish(setMealDto);
    }

    /**
     * 分页查询套餐信息
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get_set_meal_page", method = RequestMethod.GET)
    public BaseResponse<Page<SetMealDto>> getSetMealPage(
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
            @RequestParam(value = "page_size", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "name", required = false) String name
    ) {
        return setMealService.getSetMealByPage(pageNum, pageSize, name);
    }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public BaseResponse<String> deleteSetMeal(@RequestParam(value = "id") List<Long> ids) {
        return setMealService.deleteSetMealWithDishById(ids);
    }

    /**
     * 条件获取套餐信息
     * @param setMeal
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/st_list", method = RequestMethod.POST)
    public BaseResponse<List<SetMeal>> querySetMealByCondition(SetMeal setMeal) {
        return setMealService.querySetMealLsitByCondition(setMeal);
    }
}
