package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse<String> add(@RequestBody Category category) {
        log.info("新增的category:{}",category);
        return categoryService.add(category);
    }

    /**
     * 分页查询分类List
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/category_list", method = RequestMethod.GET)
    public BaseResponse<Page<Category>> getCategoryList(
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
            @RequestParam(value = "page_size", defaultValue = "5", required = false) int pageSize
    ) {
        return categoryService.getCategoryListByPage(pageNum, pageSize);
    }

    /**
     * 根据id更新分类
     * @param category
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<String> update(@RequestBody Category category) {
        log.info("更新的category:{}",category);
        return categoryService.updateCategoryById(category);
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public BaseResponse<String> delete(@RequestParam(value = "id", required = true) Long id) {
        return categoryService.deleteCategoryById(id);
    }

    /**
     * 根据条件查询分类数据
     * @param category
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get_category_by_condition", method = RequestMethod.POST)
    public BaseResponse<List<Category>> getCategoryByCondition(@RequestBody Category category) {
        List<Category> categories = categoryService.queryCategoryListByCondition(category);
        if (CollectionUtils.isEmpty(categories)) {
            return BaseResponse.error("未查询到分类");
        }
        return BaseResponse.success(categories);
    }
}
