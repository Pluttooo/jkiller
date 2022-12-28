package com.itheima.reggie.dto;

import com.itheima.reggie.entity.SetMeal;
import com.itheima.reggie.entity.SetMealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetMealDto extends SetMeal {

    private List<SetMealDish> setMealDishes;

    private String categoryName;
}
