package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(value = {"com.itheima.service"})
@PropertySource(value = "classpath:jdbc.properties")
@Import(value = {JDBCConfig.class, MybatisConfig.class})
@EnableTransactionManagement
public class SpringConfig {
}
