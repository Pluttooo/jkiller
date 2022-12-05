package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.entity.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    // @Autowired
    // private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse<Employee> loginController(HttpServletRequest request, @RequestBody Employee employee) {
        // 把用户密码md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 根据username查询employee信息
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeMapper.selectOne(lambdaQueryWrapper);

        if (null == emp || !emp.getPassword().equals(employee.getPassword())) {
            return BaseResponse.error("登录失败");
        }
        if (emp.getStatus() == 0) {
            return BaseResponse.error("账号已禁用");
        }
        // 登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return BaseResponse.success(emp);
    }
}
