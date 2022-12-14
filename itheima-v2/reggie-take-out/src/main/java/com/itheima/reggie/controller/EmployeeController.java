package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.BaseResponse;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping(value = "/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 登录
     *
     * @param request
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        // 把用户密码md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info("传入的employee_password=" + password);
        // 根据username查询employee信息
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeMapper.selectOne(employeeLambdaQueryWrapper);
        if (null == emp || password.equals(emp.getPassword())) {
            return BaseResponse.error("登录失败");
        }
        if (emp.getStatus() == 0) {
            return BaseResponse.error("账号已禁用");
        }
        // 登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return BaseResponse.success(emp);
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BaseResponse<String> logout(HttpServletRequest request) {
        // 清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return BaseResponse.success("退出成功");
    }

    /**
     * 新增员工
     *
     * @param request
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse<String> addEmployee(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息为：{}", employee);
        // Long employeeId = (Long) request.getSession().getAttribute("employee");
        return employeeService.addEmployee(employee);
    }

    /**
     * 分页查询员工信息
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp_list", method = RequestMethod.GET)
    public BaseResponse<Page<Employee>> getEmployeeList(
            @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
            @RequestParam(value = "page_size", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "name", required = false) String name) {
        return employeeService.getEmployeeList(pageNum, pageSize, name);
    }

    /**
     * 根据id更新员工信息
     *
     * @param request
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<String> updateEmployeeById(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("employee info：{}", employee);
        // Long employeeId = (Long) request.getSession().getAttribute("employee");
        // employee.setId(employeeId);
        return employeeService.updateEmployeeById(employee);
    }

    @ResponseBody
    @RequestMapping(value = "/get_employee/{id}", method = RequestMethod.GET)
    public BaseResponse<Employee> getEmployeeById(@PathVariable(value = "id") Long id) {
        return employeeService.getEmployeeById(id);
    }
}
