package com.itheima.mapper;

import com.itheima.pojo.Brand;

import java.util.List;

public interface BrandMapper {

    /**
     * 查询所有brand
     * @return
     */
    List<Brand> selectAllBrands();

    /**
     * 通过id查询brand
     * @param id
     * @return
     */
    Brand selectBrandById(int id);

    /**
     * 多条件动态SQL查询
     * @param brand
     * @return
     */
    List<Brand> selectBrandByCondition(Brand brand);

    /**
     * 单条件动态SQL查询
     * @param brand
     * @return
     */
    List<Brand> selectBrandBySingleCondition(Brand brand);
}
