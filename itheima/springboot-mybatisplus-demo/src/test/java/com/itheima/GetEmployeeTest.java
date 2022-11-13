package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.domain.EmployeeDao;
import com.itheima.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GetEmployeeTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void getAllEmployee() {
        List<Employee> employees = employeeDao.selectList(null);
        System.out.println("employees=" + employees);
    }

    @Test
    void getEmployeeByEmpId() {
        Employee employee = employeeDao.selectById(1005);
        System.out.println("employee=" + employee);
    }

    @Test
    void getEmployeeByWrapper() {
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
    }
}
