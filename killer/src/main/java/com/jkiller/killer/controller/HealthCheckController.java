package com.jkiller.killer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/jkiller")
public class HealthCheckController {

    @ResponseBody
    @RequestMapping(value = "/health_check", method = RequestMethod.GET)
    public String HealthCheck() {
        return "it's ok!";
    }
}
