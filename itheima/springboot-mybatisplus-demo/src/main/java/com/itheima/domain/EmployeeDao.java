package com.itheima.domain;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {
}
