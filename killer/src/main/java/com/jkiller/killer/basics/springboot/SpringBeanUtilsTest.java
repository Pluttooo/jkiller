package com.jkiller.killer.basics.springboot;

import org.springframework.beans.BeanUtils;

public class SpringBeanUtilsTest {

    // 深拷贝 浅拷贝
    public static void main(String[] args) {
        PersonSource personSource = new PersonSource();
        personSource.setId(1);
        personSource.setUsername("pjmike");
        personSource.setPassword("12345");
        personSource.setAge(25);
        PersonDest personDest = new PersonDest();
        BeanUtils.copyProperties(personSource, personDest);
        System.out.println("personSource: " + personSource.toString());
        System.out.println("personDest: " + personDest.toString());
    }
}
