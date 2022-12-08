package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.Employee;

public interface EmployeeService {

    BaseResponse<String> addEmployee(Employee employee);

    BaseResponse<Page<Employee>> getEmployeeList(int page, int pageSize, String name);

}
