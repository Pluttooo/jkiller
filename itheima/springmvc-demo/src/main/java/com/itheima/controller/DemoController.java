package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/spring_mvc_controller_demo")
public class DemoController {

    @RequestMapping(value = "/demo1", method = RequestMethod.GET)
    @ResponseBody
    public String controllerDemo1() {
        return "spring controller demo1";
    }

    @RequestMapping(value = "demo2", method = RequestMethod.GET)
    @ResponseBody
    public String controllerDemo2() {
        return "spring controller demo2";
    }
}
