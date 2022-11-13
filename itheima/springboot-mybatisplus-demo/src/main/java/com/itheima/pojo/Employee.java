package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "db1.emp")
public class Employee {
    private Integer empId;
    private String empName;
    private Integer jobId;
    private String mgr;
    private String joinDate;
    private Double salary;
    private Double bonus;
    private Integer deptId;
}
