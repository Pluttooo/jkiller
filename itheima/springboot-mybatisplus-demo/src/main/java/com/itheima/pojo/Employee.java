package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.context.annotation.Primary;

@Data
@TableName(value = "db1.emp")
public class Employee {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer empId;
    private String empName;
    private Integer jobId;
    private String mgr;
    private String joinDate;
    @TableField(select = false)
    private Double salary;
    private Double bonus;
    private Integer deptId;
    @TableField(exist = false)
    private Long timestamp;
}
