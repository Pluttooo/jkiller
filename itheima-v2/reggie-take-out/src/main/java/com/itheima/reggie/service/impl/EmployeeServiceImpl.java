package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public BaseResponse<String> addEmployee(Employee employee) {
        // 设置员工信息
        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        // 写死添加人为admin
        // Long employeeId = 1L;
        // 往数据库传入数据
        employeeMapper.insert(employee);
        return BaseResponse.success("新增员工成功");
    }

    @Override
    public BaseResponse<Page<Employee>> getEmployeeList(int pageNum, int pageSize, String name) {
        // 分页构造器
        Page<Employee> pageInfo = new Page<>(pageNum, pageSize);
        // 添加过滤条件
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        // 添加排序
        employeeLambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
        // 执行查询
        employeeMapper.selectPage(pageInfo, employeeLambdaQueryWrapper);
        return BaseResponse.success(pageInfo);
    }

    @Override
    public BaseResponse<String> updateEmployeeById(Employee employee) {
        employeeMapper.updateById(employee);
        return BaseResponse.success("更新成功");
    }

    @Override
    public BaseResponse<Employee> getEmployeeById(Long id) {
        Employee employee = employeeMapper.selectById(id);
        if (employee == null) {
            return BaseResponse.error("未查询到该员工信息");
        }
        return BaseResponse.success(employee);
    }
}
