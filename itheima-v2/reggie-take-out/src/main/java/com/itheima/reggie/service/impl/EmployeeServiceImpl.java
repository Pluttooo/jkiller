package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.entity.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.time.Period;


@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public BaseResponse<String> addEmployee(Employee employee) {
        // 设置员工信息
        employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        // Long employeeId = (Long) request.getSession().getAttribute("employee");
        // 写死添加人为admin
        Long employeeId = 1L;
        employee.setCreateUser(employeeId);
        employee.setUpdateUser(employeeId);
        // 往数据库传入数据
        employeeMapper.insert(employee);
        return BaseResponse.success("新增员工成功");
    }

    @Override
    public BaseResponse<Page<Employee>> getEmployeeList(int page, int pageSize, String name) {
        // 分页构造器
        Page<Employee> pageInfo = new Page<>(page, pageSize);
        // 添加过滤条件
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        // 添加排序
        employeeLambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
        // 执行查询
        employeeMapper.selectPage(pageInfo, employeeLambdaQueryWrapper);
        return BaseResponse.success(pageInfo);
    }
}
